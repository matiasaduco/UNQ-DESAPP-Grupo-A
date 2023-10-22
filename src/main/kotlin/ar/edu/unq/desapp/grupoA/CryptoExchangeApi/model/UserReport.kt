package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.time.LocalDateTime

class UserReport(
    val totalUSD: Float,
    val totalARG: Float,
    val activeList: MutableList<Active>,
) {
    val requestDate: LocalDateTime = LocalDateTime.now()
}
