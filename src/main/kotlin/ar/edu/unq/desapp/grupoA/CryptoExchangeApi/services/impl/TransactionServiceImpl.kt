package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl


import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.TransactionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl : TransactionService {

    @Autowired
    lateinit var transactionRepository: TransactionRepository

    @Autowired
    lateinit var intentionRepository: IntentionRepository

    @Autowired
    lateinit var userRepository: UserRepository



    override fun createTransactation(intentionID: Int, userID: Int) : Transaction {
        val intention = intentionRepository.findById(intentionID)
            .orElseThrow { throw Exception("Error al recuperar la intención ${intentionID}") }

        if (intention.intentionCryptoPrice != intention.crypto.price){
            throw Exception("Cotización del crypto difiere al precio de la intenctión ")
        }

        val user = userRepository.findById(userID)
            .orElseThrow { throw Exception("Error al recuperar usuario ${userID}") }

        if (user.id == userID){
            throw Exception("Usuario no puede crear transacción de su propia intención")
        }

        val transaction : Transaction = Transaction(intention,user)
        val saveTransaction = transactionRepository.save(transaction)

        return saveTransaction

    }


}