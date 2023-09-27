package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Crypto(val symbol: String, var price: Float) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var time: LocalDateTime = LocalDateTime.now()
}