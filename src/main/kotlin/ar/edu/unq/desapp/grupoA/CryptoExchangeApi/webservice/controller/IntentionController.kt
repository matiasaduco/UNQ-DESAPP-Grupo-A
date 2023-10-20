package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.IntentionCreationDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.IntentionDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.TransactionDTO
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
        return try {
            val intention = intentionService.createIntention(
                intentionDTO.crypto,
                intentionDTO.cryptoNominalQuantity,
                intentionDTO.cryptoIntentionPrice,
                intentionDTO.operation,
                intentionDTO.userId
            )

            ResponseEntity.status(HttpStatus.OK).body(intention)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping
    fun getAllIntentions(): List<IntentionDTO> {
        return intentionService.getAllIntentions()
    }

   @PostMapping("/{intentionId}/transaction/{userID}")
   fun postTransaction(@PathVariable intentionId: Int, @PathVariable userID: Int) : ResponseEntity<Any>{
       return try{
           val transactionDTO = transactionService.createTransaction(intentionId, userID)
           ResponseEntity.status(HttpStatus.OK).body(transactionDTO)
       }catch (e : Exception){
           ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
       }
    }
}
