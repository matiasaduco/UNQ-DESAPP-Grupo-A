package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.TransactionActionDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.TransactionDTO
import org.springframework.stereotype.Service

@Service
interface TransactionService {

    fun createTransaction(intentionID : Int, userID: Int): TransactionDTO

    fun confirmTransaction(transactionActionDTO: TransactionActionDTO, transactionID : Int) : TransactionDTO

    fun cancelTransaction(transactionActionDTO: TransactionActionDTO, transactionID : Int): TransactionDTO

    fun advanceOnTransaction(transactionActionDTO: TransactionActionDTO, transactionID : Int): TransactionDTO
}