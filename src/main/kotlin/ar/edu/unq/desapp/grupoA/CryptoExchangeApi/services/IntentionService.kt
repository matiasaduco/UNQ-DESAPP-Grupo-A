package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import org.springframework.stereotype.Service

@Service
interface IntentionService {
    fun createIntention(
        crypto: String,
        criptoNominalQuantity: Int,
        intentionPriceInArs: Float,
        operation: IntentionType,
        userId: Int
    ): Intention

    fun getAllIntentions(): MutableIterable<Intention>
}
