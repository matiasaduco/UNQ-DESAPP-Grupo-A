package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import java.time.LocalDate
import java.time.LocalDateTime

class CryptoBuilder {

    var name : String= "no name"
    var price : Float= 0f
    var time : LocalDateTime = LocalDateTime.now()

    fun build() : Crypto {
        val crypto = Crypto(name,price)
        crypto.pricingTime = this.time
        crypto.pricingHour = this.time.hour
        return crypto
    }

    fun withName(aName:String) = apply{ this.name = aName }

    fun withPrice(aPrice:Float) = apply { this.price = aPrice }

    fun  withTime(aTime: LocalDateTime) = apply { this.time = aTime }

}