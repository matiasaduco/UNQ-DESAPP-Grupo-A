package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

class User(
    var name: String,
    var surname: String,
    var email: String,
    var address: String,
    var password: String,
    var cvu: String,
    var walletAddress: String,
) {
    var operations: Int = 0
    var reputation: Float = 0f
    var id : Int? = null
}
