package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto

class TransactionDTO(
    val cryptoSymbol: String,
    val cryptoNominalQuantity: Double,
    val cryptoIntentionPrice: Float,
    val priceInARS: Double,
    val user: String,
    val userReputation: String,
    val address: String,
    val action: String
)
