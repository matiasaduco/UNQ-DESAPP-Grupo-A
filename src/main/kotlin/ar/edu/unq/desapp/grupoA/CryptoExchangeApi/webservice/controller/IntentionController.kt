package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.IntentionCreationDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/intention")
class IntentionController(private val intentionService: IntentionService) {
    @PostMapping
    fun createIntention(@RequestBody intentionDTO: IntentionCreationDTO): ResponseEntity<Any> {
        return try {
            val intention = intentionDTO.toModel()
            val intentionCreationDTO = IntentionCreationDTO.fromModel(intentionService.createIntention(intention))
            ResponseEntity.status(HttpStatus.OK).body(intentionCreationDTO)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e)
        }
    }

    @GetMapping
    fun getAllIntentions(): MutableIterable<Intention> {
        return intentionService.getAllIntentions()
    }
}
