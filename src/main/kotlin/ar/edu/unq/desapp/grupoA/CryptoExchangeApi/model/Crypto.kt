package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.time.LocalDate
import java.time.LocalDateTime

class Crypto(val symbol: String, var price: Float) {
    var time : LocalDateTime = LocalDateTime.now()
}