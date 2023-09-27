package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User

class IntentionCreationDTO(
    val crypto: Crypto,
    val criptoNominalQuantity: Int,
    val intentionPriceInArs: Float,
    val operation: IntentionType,
    val user: User,
) {
    fun toModel(): Intention {
        return Intention(
            crypto = this.crypto,
            criptoNominalQuantity = this.criptoNominalQuantity,
            intentionPriceInArs = this.intentionPriceInArs,
            operation = this.operation,
            user = this.user,
        )
    }

    companion object {
        fun fromModel(intention: Intention): IntentionCreationDTO {
            return IntentionCreationDTO(
                intention.crypto,
                intention.criptoNominalQuantity,
                intention.intentionPriceInArs,
                intention.operation,
                intention.user
            )
        }
    }
}
