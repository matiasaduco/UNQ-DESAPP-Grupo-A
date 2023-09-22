package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.time.LocalDate

class Intention(
    val crypto: Crypto,
    val criptoNominalQuantity: Int,
    val intentionPrice: Float,
    val operation: IntentionType,
    val user: User
) {
    val creationDate: LocalDate = LocalDate.now()
    var id: Int? = null
}

enum class IntentionType {
    BUY, SALE
}
