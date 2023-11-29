package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionDTO
import org.springframework.stereotype.Service

@Service
interface TransactionService {

    fun createTransaction(intentionID: Int): TransactionDTO

    fun confirmTransaction(transactionID: Int): TransactionDTO

    fun cancelTransaction(transactionID: Int): TransactionDTO

    fun advanceOnTransaction(transactionID: Int): TransactionDTO
}
