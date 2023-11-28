package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class BinancyProxyService {
    private var restTemplate: RestTemplate = RestTemplate()
    private var bincanceApiURL: String = ("https://api1.binance.com/api/v3/")

    fun getCryptoCurrencyValue(symbol: String): Crypto {
        val entity: Crypto? =
            restTemplate.getForObject(bincanceApiURL + "ticker/price?symbol=" + symbol, Crypto::class.java)
        return entity!!
    }

    fun getAllCryptoCurrencyValues(symbols: String): Array<Crypto> {
        val response: ResponseEntity<Array<Crypto>> =
            restTemplate.getForEntity(bincanceApiURL + "ticker/price?symbols=" + symbols, Array<Crypto>::class.java)

        return response.body!!
    }
}
