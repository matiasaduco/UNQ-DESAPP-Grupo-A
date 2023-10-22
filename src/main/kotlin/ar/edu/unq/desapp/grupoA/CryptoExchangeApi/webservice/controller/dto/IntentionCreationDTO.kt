package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType

class IntentionCreationDTO(
    val crypto: String,
    val cryptoNominalQuantity: Int,
    val cryptoIntentionPrice: Float,
    val operation: IntentionType,
    val userId: Int,
) {
    companion object {
        fun fromModel(intention: Intention): IntentionCreationDTO {
            return IntentionCreationDTO(
                intention.crypto.symbol,
                intention.criptoNominalQuantity,
                intention.intentionCryptoPrice,
                intention.operation,
                intention.user.id!!
            )
        }
    }
}
