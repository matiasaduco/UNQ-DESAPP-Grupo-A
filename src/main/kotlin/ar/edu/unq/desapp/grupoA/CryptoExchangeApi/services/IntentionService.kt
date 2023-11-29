package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.IntentionDTO
import org.springframework.stereotype.Service

@Service
interface IntentionService {
    fun createIntention(
        crypto: String,
        criptoNominalQuantity: Double,
        intentionCryptoPrice: Float,
        operation: IntentionType,
    ): IntentionDTO

    fun getAllIntentions(): List<IntentionDTO>
}
