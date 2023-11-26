package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.IntentionCreationDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.TransactionActionDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/intention")
class IntentionController(private val intentionService: IntentionService, private val transactionService: TransactionService) {
    @PostMapping
    fun createIntention(
        @RequestBody intentionDTO: IntentionCreationDTO,
    ): ResponseEntity<Any> {
        val intention = intentionService.createIntention(
            intentionDTO.crypto,
            intentionDTO.cryptoNominalQuantity,
            intentionDTO.cryptoIntentionPrice,
            intentionDTO.operation,
            intentionDTO.userId
        )

        return ResponseEntity.status(HttpStatus.OK).body(intention)
    }

    @GetMapping
    fun getAllIntentions(): ResponseEntity<Any> {
        val intentions = intentionService.getAllIntentions()
        return ResponseEntity.status(HttpStatus.OK).body(intentions)
    }

   @PostMapping("/{intentionId}/transaction")
   fun postTransaction(@PathVariable intentionId: Int, @RequestBody transactionActionDTO: TransactionActionDTO) : ResponseEntity<Any>{

       val transactionDTO = transactionService.createTransaction(intentionId,transactionActionDTO)
       return ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
   }

}
