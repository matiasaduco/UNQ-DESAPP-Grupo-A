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
    var reputationPoints: Int = 0
    var id: Int? = null


    fun getReputation() : Double {
        return this.reputationPoints / this.operations.toDouble()
    }
    fun cancelTransaction() {
        this.reputationPoints = (this.reputationPoints - 20).coerceAtLeast(0)
    }

    fun acceptTransactionUnder30minutes() {
        this.reputationPoints += 10
        this.operations += 1
    }

    fun acceptTransactionOver30minutes() {
        this.reputationPoints += 5
        this.operations += 1
    }
}