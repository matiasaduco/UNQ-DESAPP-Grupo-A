package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class CryptoTest{

    @Test
    fun initializeCriptoHasAName(){
        val criptoName : String = "ALICEUSDT"
        val crypto : Crypto = Crypto(criptoName, 0.768f)

        Assertions.assertEquals(crypto.symbol, criptoName)
    }

    @Test
    fun initialzeCriptoHasAPrice(){
        val price : Float = 0.768f
        val crypto : Crypto = Crypto("ALICEUSDT", price,)
        Assertions.assertEquals(crypto.price, price)
    }

    @Test
    fun  initializeCriptoHasATime(){
        val now : LocalDateTime = LocalDateTime.now()
        val crypto : Crypto = Crypto("ALICEUSDT",0.768f )
        crypto.time = now
        Assertions.assertEquals(crypto.time, now)
    }

}