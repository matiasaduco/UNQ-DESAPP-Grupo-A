package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType

class IntentionCreationDTO(
    val crypto: String,
    val criptoNominalQuantity: Int,
    val intentionPriceInArs: Float,
    val operation: IntentionType,
    val userId: Int,
) {
    companion object {
        fun fromModel(intention: Intention): IntentionCreationDTO {
            return IntentionCreationDTO(
                intention.crypto.symbol,
                intention.criptoNominalQuantity,
                intention.intentionPriceInArs,
                intention.operation,
                intention.user.id!!
            )
        }
    }
}
