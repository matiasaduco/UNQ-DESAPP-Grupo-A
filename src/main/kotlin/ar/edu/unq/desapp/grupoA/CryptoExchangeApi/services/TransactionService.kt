package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import org.springframework.stereotype.Service

@Service
interface TransactionService {

    fun createTransactation(intentionID : Int, userID: Int):Transaction
}