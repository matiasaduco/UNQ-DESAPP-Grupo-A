package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import java.time.LocalDate

class Cripto(val name: String, var price: Float, val time: LocalDate) {
    var id: Int? = null
}