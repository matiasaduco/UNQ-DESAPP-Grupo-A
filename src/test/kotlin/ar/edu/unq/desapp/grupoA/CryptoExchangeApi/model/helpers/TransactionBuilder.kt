package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.*

class TransactionBuilder {
    var crypto: Crypto = CryptoBuilder().build()
    var criptoNominalQuantity: Double = 0.0
    var intentionPrice: Float = 0f
    var operation: IntentionType = IntentionType.BUY
    var user: User = UserBuilder().build()


    fun build(): Intention {
        val transaction = Intention(crypto, criptoNominalQuantity, intentionPrice, operation, user)
        return transaction
    }

    fun withCripto(aCrypto: Crypto) = apply { this.crypto = aCrypto }

    fun withCritoNominalPrice(aCriptoNominalQuantity: Double) =
        apply { this.criptoNominalQuantity = aCriptoNominalQuantity }

    fun withIntentionPrice(aIntentionPrice: Float) =
        apply { this.intentionPrice = aIntentionPrice }

    fun withOperation(aOperation : IntentionType) = apply { this.operation = aOperation }

    fun withUser(aUser : User) = apply { this.user = aUser }
}