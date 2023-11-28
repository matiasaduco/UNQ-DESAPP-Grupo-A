package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl


import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.*
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.TransactionState
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.TransactionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.DolarProxyService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionActionDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionDTO
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TransactionServiceImpl : TransactionService {

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var intentionRepository: IntentionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var dolarProxyService: DolarProxyService

    @Autowired
    lateinit var tokenService: TokenService

    override fun createTransaction(intentionID: Int): TransactionDTO {
        val intention = intentionRepository.findById(intentionID)
            .orElseThrow { IntentionCannotBeFoundException(intentionID) }

        val user = userRepository.findFirstByEmail(tokenService.getEmail())
            .orElseThrow { UserDosentExists() }

        if (intention.user.id == user.id) {
            throw TransactionCannotAdvance("Usuario no puede crear transacción de su propia intención")
        }

        if (intention.isFinished){
            throw TransactionCannotAdvance("Intención ha finalizado")
        }

        val transaction = Transaction(intention, user)
        val saveTransaction = transactionRepository.save(transaction)

        val priceInArs =
            dolarProxyService.getPriceInArs(transaction.intention.cryptoNominalQuantity * transaction.intention.intentionCryptoPrice)

        val transactionDTO = TransactionDTO(
            saveTransaction.intention.crypto.symbol,
            saveTransaction.intention.cryptoNominalQuantity,
            saveTransaction.intention.intentionCryptoPrice,
            priceInArs,
            user.getFullname(),
            user.getReputation(),
            intention.addressToSend(),
            ""
        )

        return transactionDTO
    }

    override fun confirmTransaction(transactionID: Int): TransactionDTO {
        val transaction = transactionRepository.findById(transactionID)
            .orElseThrow { TransactionCannotBeFound(transactionID) }

        val user = userRepository.findFirstByEmail(tokenService.getEmail())
            .orElseThrow { loginErrorException() }

        if (user.id != transaction.intention.user.id) {
            throw loginErrorException()
        }

        if (transaction.transactionState != TransactionState.TRANSFERED) {
            throw TransactionCannotAdvance("Transacción no fue continuada")
        }

        if (transaction.intention.canBeConfirmed()) {
            saveConfirmedTransaction(transaction)
        } else {
            transaction.transactionState = TransactionState.CANCELED
            transactionRepository.save(transaction)
            throw PriceOutOfRangeException("Discrepancia en precio de la cripto")
        }

        val priceInArs =
            dolarProxyService.getPriceInArs(transaction.intention.cryptoNominalQuantity * transaction.intention.intentionCryptoPrice)

        return TransactionDTO(
            transaction.intention.crypto.symbol,
            transaction.intention.cryptoNominalQuantity,
            transaction.intention.intentionCryptoPrice,
            priceInArs,
            transaction.userInterested.getFullname(),
            transaction.userInterested.getReputation(),
            transaction.intention.addressToSend(),
            "Confirmar la recepción"
        )

    }

    private fun saveConfirmedTransaction(transaction: Transaction) {
        transaction.intention.isFinished = true
        transaction.transactionState = TransactionState.FINISHED

        transactionRepository.save(transaction)
        intentionRepository.save(transaction.intention)

        val userInterested = transaction.userInterested
        val intentionUser = transaction.intention.user
        if (LocalDateTime.now().isBefore(transaction.createdAt.plusMinutes(30))) {
            userInterested.acceptTransactionUnder30minutes()
            intentionUser.acceptTransactionUnder30minutes()
        } else {
            userInterested.acceptTransactionOver30minutes()
            intentionUser.acceptTransactionOver30minutes()
        }
        userRepository.save(userInterested)
        userRepository.save(intentionUser)
    }

    override fun cancelTransaction(transactionID: Int): TransactionDTO {
        val transaction = transactionRepository.findById(transactionID)
            .orElseThrow { TransactionCannotBeFound(transactionID) }

        val userInterested = transaction.userInterested
        val intentionUser = transaction.intention.user

        val user = userRepository.findFirstByEmail(tokenService.getEmail()).orElseThrow{UserDosentExists()}

        if (userInterested.email == user.email && userInterested.password == user.password) {
            userInterested.cancelTransaction()
            userRepository.save(userInterested)
        } else if (intentionUser.email == user.email && userInterested.password == user.password) {
            intentionUser.cancelTransaction()
            userRepository.save(intentionUser)
        } else {
            throw TransactionCannotAdvance("Error en transaccion")
        }

        transaction.transactionState = TransactionState.CANCELED
        transactionRepository.save(transaction)

        val priceInArs =
            dolarProxyService.getPriceInArs(transaction.intention.cryptoNominalQuantity * transaction.intention.intentionCryptoPrice)

        return TransactionDTO(
            transaction.intention.crypto.symbol,
            transaction.intention.cryptoNominalQuantity,
            transaction.intention.intentionCryptoPrice,
            priceInArs,
            transaction.userInterested.getFullname(),
            transaction.userInterested.getReputation(),
            transaction.intention.addressToSend(),
            "Cancelar"
        )
    }

    override fun advanceOnTransaction(transactionID: Int): TransactionDTO {

        val transaction = transactionRepository.findById(transactionID)
            .orElseThrow { TransactionCannotBeFound(transactionID) }

        val user = userRepository.findFirstByEmail(tokenService.getEmail())
            .orElseThrow { loginErrorException() }

        if (user.password != transaction.userInterested.password) {
            throw loginErrorException()
        }

        transaction.transactionState = TransactionState.TRANSFERED
        transactionRepository.save(transaction)

        val priceInArs =
            dolarProxyService.getPriceInArs(transaction.intention.cryptoNominalQuantity * transaction.intention.intentionCryptoPrice)

        return TransactionDTO(
            transaction.intention.crypto.symbol,
            transaction.intention.cryptoNominalQuantity,
            transaction.intention.intentionCryptoPrice,
            priceInArs,
            transaction.userInterested.getFullname(),
            transaction.userInterested.getReputation(),
            transaction.intention.addressToSend(),
            "Realice la transferencia"
        )
    }

}
