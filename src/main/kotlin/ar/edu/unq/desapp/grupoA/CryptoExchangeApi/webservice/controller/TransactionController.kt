package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionActionDTO
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "Avanzar en la transacción de parte del usuario interesado")
    @PostMapping("/{transactionID}/advance")
    fun advanceOnTransaction(
        @PathVariable transactionID: Int
    ): ResponseEntity<Any> {
        val transactionDTO = transactionService.advanceOnTransaction(transactionID)
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
    }

    @Operation(summary = "El usuario que publico la intención confirma la transacción")
    @PostMapping("/{transactionID}/confirm")
    fun confirmTransaction(
        @PathVariable transactionID: Int
    ): ResponseEntity<Any> {
        val transactionDTO = transactionService.confirmTransaction(transactionID)
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
    }


    @Operation(summary = "Cualquiera de las partes de la transacción la cancela")
    @PostMapping("/{transactionID}/cancel")
    fun cancelTransaction(
        @RequestBody transactionActionDTO: TransactionActionDTO,
        @PathVariable transactionID: Int
    ): ResponseEntity<Any> {
        val transactionDTO = transactionService.cancelTransaction(transactionID)
        return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
    }
}
