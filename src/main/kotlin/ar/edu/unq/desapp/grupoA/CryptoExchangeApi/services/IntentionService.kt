package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.IntentionDTO
import org.springframework.stereotype.Service

@Service
interface IntentionService {
    fun createIntention(
        crypto: String,
        criptoNominalQuantity: Int,
        intentionCryptoPrice: Float,
        operation: IntentionType,
        userId: Int
    ): IntentionDTO

    fun getAllIntentions(): List<IntentionDTO>
}
