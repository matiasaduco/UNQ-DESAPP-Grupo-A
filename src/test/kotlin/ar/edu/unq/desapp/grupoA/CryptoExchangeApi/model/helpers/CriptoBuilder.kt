package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Cripto
import java.time.LocalDate

class CriptoBuilder {

    var name : String= "no name"
    var price : Float= 0f
    var time : LocalDate = LocalDate.now()

    fun build() : Cripto {
        val cripto = Cripto(name,price,time)
        return cripto
    }

    fun withName(aName:String) = apply{ this.name = aName }

    fun withPrice(aPrice:Float) = apply { this.price = aPrice }

    fun  withTime(aTime: LocalDate) = apply { this.time = aTime }

}