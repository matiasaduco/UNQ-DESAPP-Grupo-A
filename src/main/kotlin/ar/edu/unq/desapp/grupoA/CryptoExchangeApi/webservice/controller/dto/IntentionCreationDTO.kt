package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User

class IntentionCreationDTO(
    val crypto: Crypto,
    val criptoNominalQuantity: Int,
    val intentionPrice: Float,
    val operation: IntentionType,
    val user: User,
) {
    fun toModel(): Intention {
        return Intention(
            crypto = this.crypto,
            criptoNominalQuantity = this.criptoNominalQuantity,
            intentionPrice = this.intentionPrice,
            operation = this.operation,
            user = this.user,
        )
    }

    companion object {
        fun fromModel(intention: Intention): IntentionCreationDTO {
            return IntentionCreationDTO(
                intention.crypto,
                intention.criptoNominalQuantity,
                intention.intentionPrice,
                intention.operation,
                intention.user
            )
        }
    }
}
