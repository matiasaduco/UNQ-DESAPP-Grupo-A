package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.IntentionCreationDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionActionDTO
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/intention")
class IntentionController(private val intentionService: IntentionService, private val transactionService: TransactionService) {
    @Operation(summary ="Publicar una intecion de compra/venta de cryptos")
    @PostMapping
    fun createIntention(
        @RequestBody intentionDTO: IntentionCreationDTO,
    ): ResponseEntity<Any> {
        val intention = intentionService.createIntention(
            intentionDTO.crypto,
            intentionDTO.cryptoNominalQuantity,
            intentionDTO.cryptoIntentionPrice,
            intentionDTO.operation,
        )

        return ResponseEntity.status(HttpStatus.OK).body(intention)
    }
    @Operation(summary = "Obtener todas las intenciones creadas por los usuarios")
    @GetMapping
    fun getAllIntentions(): ResponseEntity<Any> {
        val intentions = intentionService.getAllIntentions()
        return ResponseEntity.status(HttpStatus.OK).body(intentions)
    }

    @Operation(summary = "Postear una transacción sobre la intencion de id dada")
    @PostMapping("/{intentionId}/transaction")
    fun postTransaction(@PathVariable intentionId: Int) : ResponseEntity<Any>{

       val transactionDTO = transactionService.createTransaction(intentionId)
       return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
   }

}
