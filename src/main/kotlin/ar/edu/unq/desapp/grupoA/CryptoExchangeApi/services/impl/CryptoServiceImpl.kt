package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.CryptoRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.BinancyProxyService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CryptoServiceImpl : CryptoService {

    @Autowired
    lateinit var binaceProxyService: BinancyProxyService

    @Autowired
    lateinit var cryptoRepository: CryptoRepository

    val cryptoSymbols: List<String> = listOf(
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

    @PostConstruct
    override fun getCryptosPrice(): List<Crypto> {
        val cryptos: MutableList<Crypto> = mutableListOf()
        cryptoSymbols.forEach {
            val crypto : Crypto = getCryptoPrice(it)
            cryptos.add(crypto)
            cryptoRepository.save(crypto)
        }

        return cryptos.toList()
    }

    override fun getCryptoPrice(symbol: String): Crypto {
        val entity: Crypto = binaceProxyService.getCryptoCurrencyValue(symbol)

        entity.pricingTime = LocalDateTime.now()

        return entity
    }

}
