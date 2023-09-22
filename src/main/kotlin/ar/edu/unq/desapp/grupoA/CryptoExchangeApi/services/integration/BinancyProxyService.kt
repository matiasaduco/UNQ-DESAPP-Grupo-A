package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class BinancyProxyService {

    private var restTemplate : RestTemplate = RestTemplate()

    private var bincanceApiURL : String = ("https://api1.binance.com/api/v3/")


    fun getCryptoCurrencyValue(symbol: String): Crypto{
        val entity: Crypto? = restTemplate.getForObject(bincanceApiURL + "ticker/price?symbol=" + symbol, Crypto::class.java )
        return entity!!
    }
}