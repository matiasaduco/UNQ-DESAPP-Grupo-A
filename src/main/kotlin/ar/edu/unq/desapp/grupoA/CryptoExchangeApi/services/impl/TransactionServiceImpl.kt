package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl


import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.TransactionState
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.TransactionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.TransactionActionDTO
import org.springframework.beans.factory.annotation.Autowired
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



    override fun createTransaction(intentionID: Int, userID: Int) : Transaction {
        val intention = intentionRepository.findById(intentionID)
            .orElseThrow { Exception("Error al recuperar la intención ${intentionID}") }


        val user = userRepository.findById(userID)
            .orElseThrow { Exception("Error al recuperar usuario ${userID}") }

        if (intention.user.id == userID){
            throw Exception("Usuario no puede crear transacción de su propia intención")
        }

        val transaction : Transaction = Transaction(intention,user)
        val saveTransaction = transactionRepository.save(transaction)

        return saveTransaction

    }



    override fun confirmTransaction(transactionActionDTO: TransactionActionDTO) {
        val transaction = transactionRepository.findById(transactionActionDTO.transactionID)
                .orElseThrow { Exception("Transacción ${transactionActionDTO.transactionID} no encontrada") }


        val user = userRepository.findFirstByEmail(transactionActionDTO.email)
                .orElseThrow { Exception("Usuario o contraseña erroneos") }

        if (user.password != transactionActionDTO.password || user.id != transaction.intention.user.id){
            throw Exception("Usuario o contraseña erroneos")
        }

        if (transaction.transactionState != TransactionState.TRANSFERED){
            throw Exception("Transacción no fue contiuanda")
        }

        confirmTransaction(transaction)




    }



    private fun confirmTransaction(transaction: Transaction) {
        transaction.intention.isFinished = true
        transaction.transactionState = TransactionState.FINISHED

        transactionRepository.save(transaction)
        intentionRepository.save(transaction.intention)

        val userInterested = transaction.userInterested
        val intentionUser = transaction.intention.user
        if (LocalDateTime.now().isBefore( transaction.createdAt.plusMinutes(30) )){

            userInterested.acceptTransactionUnder30minutes()
            intentionUser.acceptTransactionUnder30minutes()

        }else{

            userInterested.acceptTransactionOver30minutes()
            intentionUser.acceptTransactionOver30minutes()


        }
        userRepository.save(userInterested)
        userRepository.save(intentionUser)
    }

    override fun cancelTransaction(transactionActionDTO: TransactionActionDTO) {
        val transaction = transactionRepository.findById(transactionActionDTO.transactionID)
                .orElseThrow { Exception("Transacción ${transactionActionDTO.transactionID} no encontrada") }


        val userInterested = transaction.userInterested
        val intentionUser = transaction.intention.user

        if (userInterested.email == transactionActionDTO.email && userInterested.password == transactionActionDTO.password){
            userInterested.cancelTransaction()
            userRepository.save(userInterested)
        }else if (intentionUser.email == transactionActionDTO.email && userInterested.password == transactionActionDTO.password){
            intentionUser.cancelTransaction()
            userRepository.save(intentionUser)
        }else{
            throw Exception("Transaction incorrecta")
        }

        transaction.transactionState = TransactionState.CANCELED
        transactionRepository.save(transaction)
    }

    override fun advanceOnTransaction(transactionActionDTO: TransactionActionDTO) {
        val transaction = transactionRepository.findById(transactionActionDTO.transactionID)
                .orElseThrow { Exception("Transacción ${transactionActionDTO.transactionID} no encontrada") }

        val user = userRepository.findFirstByEmail(transactionActionDTO.email)
                .orElseThrow { Exception("Usuario o contraseña erroneos") }

        if (user.password != transaction.userInterested.password ){
            throw Exception("Usuario o contraseña erroneos")
        }

        transaction.transactionState = TransactionState.TRANSFERED
        transactionRepository.save(transaction)

    }

}