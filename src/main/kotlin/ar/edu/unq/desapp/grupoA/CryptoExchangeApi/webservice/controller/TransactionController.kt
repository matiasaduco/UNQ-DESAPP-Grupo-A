package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionActionDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(private val transactionService: TransactionService) {

    @PostMapping("/{transactionID}/advance")
    fun advanceOnTransaction(
        @RequestBody transactionActionDTO: TransactionActionDTO,
        @PathVariable transactionID: Int
    ): ResponseEntity<Any> {
        val transactionDTO = transactionService.advanceOnTransaction(transactionActionDTO, transactionID)
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
    }

    @PostMapping("/{transactionID}/confirm")
    fun confirmTransaction(
        @RequestBody transactionActionDTO: TransactionActionDTO,
        @PathVariable transactionID: Int
    ): ResponseEntity<Any> {
        val transactionDTO = transactionService.confirmTransaction(transactionActionDTO, transactionID)
       return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
    }

    @PostMapping("/{transactionID}/cancel")
    fun cancelTransaction(
        @RequestBody transactionActionDTO: TransactionActionDTO,
        @PathVariable transactionID: Int
    ): ResponseEntity<Any> {
        val transactionDTO = transactionService.cancelTransaction(transactionActionDTO, transactionID)
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
    }
}
