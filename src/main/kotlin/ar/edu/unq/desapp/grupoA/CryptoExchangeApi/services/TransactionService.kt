package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.TransactionActionDTO
import org.springframework.stereotype.Service

@Service
interface TransactionService {

    fun createTransaction(intentionID : Int, userID: Int):Transaction

    fun confirmTransaction(transactionActionDTO: TransactionActionDTO)

    fun cancelTransaction(transactionActionDTO: TransactionActionDTO)

    fun advanceOnTransaction(transactionActionDTO: TransactionActionDTO)
}