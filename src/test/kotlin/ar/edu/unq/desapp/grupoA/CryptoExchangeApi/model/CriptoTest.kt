package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.util.Assert
import java.time.LocalDate

class CriptoTest{

    @Test
    fun initializeCriptoHasAName(){
        val criptoName : String = "ALICEUSDT"
        val cripto : Cripto = Cripto(criptoName, 0.768f, LocalDate.now())

        Assertions.assertEquals(cripto.name, criptoName)
    }

    @Test
    fun initialzeCriptoHasAPrice(){
        val price : Float = 0.768f
        val cripto : Cripto = Cripto("ALICEUSDT", price, LocalDate.now())
        Assertions.assertEquals(cripto.price, price)
    }

    @Test
    fun  initializeCriptoHasATime(){
        val now : LocalDate = LocalDate.now()
        val cripto : Cripto = Cripto("ALICEUSDT",0.768f , now)
        Assertions.assertEquals(cripto.time, now)
    }

}