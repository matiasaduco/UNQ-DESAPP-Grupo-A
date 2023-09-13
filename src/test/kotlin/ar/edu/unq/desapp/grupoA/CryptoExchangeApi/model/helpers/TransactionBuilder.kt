package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.*
import java.time.LocalDate

class TransactionBuilder {
    var cripto: Cripto = CriptoBuilder().build()
    var criptoNominalQuantity: Int = 0
    var intentionPrice: Float = 0f
    var operation: IntentionType = IntentionType.BUY
    var user: User = UserBuilder().build()


    fun build(): Transaction {
        val transaction = Transaction(cripto, criptoNominalQuantity, intentionPrice, operation, user)
        return transaction
    }

    fun withCripto(aCripto: Cripto) = apply { this.cripto = aCripto }

    fun withCritoNominalPrice(aCriptoNominalQuantity: Int) =
        apply { this.criptoNominalQuantity = aCriptoNominalQuantity }

    fun withIntentionPrice(aIntentionPrice: Float) =
        apply { this.intentionPrice = aIntentionPrice }

    fun withOperation(aOperation : IntentionType) = apply { this.operation = aOperation }

    fun withUser(aUser : User) = apply { this.user = aUser }
}