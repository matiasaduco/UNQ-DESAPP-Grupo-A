package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.time.LocalDate

class Transaction(
    val intention: Intention,
    val userInterested: User
) {
    var id: Int? = null
    var createdAt: LocalDate? = null
    var transactionState: TransactionState = TransactionState.WAITING
    lateinit var address: String
}

enum class TransactionState {
    WAITING, TRANSFERED, CONFIRMED, CANCELED
}
