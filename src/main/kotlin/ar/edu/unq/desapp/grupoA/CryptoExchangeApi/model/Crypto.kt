package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Crypto(
    @Id
    val symbol: String,
    var price: Float) {

    var time: LocalDateTime = LocalDateTime.now()
}
