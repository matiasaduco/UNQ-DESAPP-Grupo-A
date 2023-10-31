package ar.edu.unq.desapp.grupoA.CryptoExchangeApi

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers.CryptoBuilder
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.BinancyProxyService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.DolarProxyService
import org.hamcrest.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import java.time.LocalDateTime

@TestConfiguration
class Configuration {



    @Bean
    @Primary
    fun binanceProxyService() : BinancyProxyService{
        val now = LocalDateTime.now()
        val crypto : Crypto = CryptoBuilder().withName("ALICEUSDT").withPrice(10.0f).withTime(now).build()
        val crypto2 : Crypto = CryptoBuilder().withName("MATICUSDT").withPrice(11.0f).withTime(now).build()

        var binancyProxyService: BinancyProxyService = mock(BinancyProxyService::class.java)

        Mockito.`when`(binancyProxyService.getAllCryptoCurrencyValues(anyString())).thenReturn(arrayOf(crypto,crypto2))



        return binancyProxyService
    }

    @Bean
    @Primary
    fun dolarProxyService() : DolarProxyService{
        var dolarProxyService: DolarProxyService = mock(DolarProxyService::class.java)

        Mockito.`when`(dolarProxyService.getPriceInArs(anyDouble())).thenReturn(1000.0)
        return dolarProxyService
    }
}