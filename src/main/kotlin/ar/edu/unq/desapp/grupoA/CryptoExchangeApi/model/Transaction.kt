package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.time.LocalDate

class Transaction(
    val cripto: Cripto,
    val criptoNominalQuantity: Int,
    val intentionPrice: Float,
    val operation: IntentionType,
    val user: User
) {

    val creationDate: LocalDate = LocalDate.now()
    var transactionState: TransactionState = TransactionState.OPEN
    var transferDate: LocalDate? = null

    var id: Int? = null

}

enum class IntentionType {
    BUY, SALE
}

enum class TransactionState {
    OPEN, IN_PROGRESS, CONCLUDED
}