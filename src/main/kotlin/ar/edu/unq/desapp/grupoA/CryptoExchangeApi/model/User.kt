package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.math.BigInteger


class User(
    var name: String,
    var surname: String,
    var email: String,
    var address: String,
    var password: String,
    var cvu: BigInteger,
    var walletAddress: Int,
) {
    var operations: Int = 0
    var reputation: Int = 0
    var id: Int? = null

    fun cancelTransaction() {
        this.reputation = (this.reputation - 20).coerceAtLeast(0)
    }

    fun acceptTransactionUnder30minutes() {
        this.reputation += 10
    }

    fun acceptTransactionOver30minutes() {
        this.reputation += 5
    }
}