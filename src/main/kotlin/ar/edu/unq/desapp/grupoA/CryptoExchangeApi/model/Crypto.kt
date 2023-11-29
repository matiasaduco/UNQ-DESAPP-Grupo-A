package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import java.io.Serializable
import java.time.LocalDateTime


@Embeddable
class CryptoId(val symbol: String, val pricingHour: Int) : Serializable

@Entity
@IdClass(CryptoId::class)
class Crypto(
    @Id
    val symbol: String,
    var price: Float
) {

    var pricingTime: LocalDateTime = LocalDateTime.now()

    @Id
    var pricingHour: Int = this.pricingTime.hour
}
