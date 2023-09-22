package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.BinancyProxyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class CryptoServiceImpl : CryptoService {

    @Autowired
    lateinit var binaceProxyService: BinancyProxyService

    val cryptoSymbols : List<String> = listOf(
            "ALICEUSDT",
            "MATICUSDT",
            "AXSUSDT",
            "AAVEUSDT",
            "ATOMUSDT",
            "NEOUSDT",
            "DOTUSDT",
            "ETHUSDT",
            "CAKEUSDT",
            "BTCUSDT",
            "BNBUSDT",
            "ADAUSDT",
            "TRXUSDT",
            "AUDIOUSDT"
    )

    override fun getCryptosPrice(): List<Crypto> {
        var cryptos : MutableList<Crypto> = mutableListOf()
        cryptoSymbols.forEach{
            cryptos.add(getCryptoPrice(it))
        }

        return cryptos.toList()
    }


    override fun getCryptoPrice(symbol: String) : Crypto{
       val entity : Crypto = binaceProxyService.getCryptoCurrencyValue(symbol)

        entity.time = LocalDateTime.now()

        return entity
    }

}