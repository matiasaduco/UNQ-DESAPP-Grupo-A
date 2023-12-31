package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.CryptoId
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.CryptoDoesntExistException
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.CryptoRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.BinancyProxyService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.CryptoDTO
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Configuration
@EnableScheduling
@Service
class CryptoServiceImpl : CryptoService {

    @Autowired
    lateinit var binaceProxyService: BinancyProxyService

    @Autowired
    lateinit var cryptoRepository: CryptoRepository

    val cryptoSymbols: String = "[\"ALICEUSDT\"," +
            "\"MATICUSDT\"," +
            "\"AXSUSDT\"," +
            "\"AAVEUSDT\"," +
            "\"ATOMUSDT\"," +
            "\"NEOUSDT\"," +
            "\"DOTUSDT\"," +
            "\"ETHUSDT\"," +
            "\"CAKEUSDT\"," +
            "\"BTCUSDT\"," +
            "\"BNBUSDT\"," +
            "\"ADAUSDT\"," +
            "\"TRXUSDT\"," +
            "\"AUDIOUSDT\"" +
            "]"


    @Cacheable("Cryptos")
    override fun getCryptosPrice(): List<CryptoDTO> {
        val cryptos = cryptoRepository.findByPricingHour(LocalDateTime.now().hour)
        val cryptosDTO: MutableList<CryptoDTO> = mutableListOf()
        cryptos.forEach {
            cryptosDTO.add(CryptoDTO.fromModel(it))
        }

        return cryptosDTO
    }

    override fun getCryptoPrice(symbol: String): CryptoDTO {
        val crypto = cryptoRepository.findById(CryptoId(symbol, LocalDateTime.now().hour))
            .orElseThrow { throw CryptoDoesntExistException() }
        return CryptoDTO.fromModel(crypto)
    }

    @PostConstruct
    @Scheduled(fixedDelay = 600000)
    @CachePut("Cryptos")
    fun getCryptosPriceFromBinance(): List<CryptoDTO> {
        val cryptos = binaceProxyService.getAllCryptoCurrencyValues(cryptoSymbols)
        val cryptosDTO: MutableList<CryptoDTO> = mutableListOf()

        cryptos.forEach {
            cryptoRepository.save(it)
            cryptosDTO.add(CryptoDTO.fromModel(it))
        }

        return cryptosDTO
    }

    override fun getCryptoDayPrice(symbol: String): List<CryptoDTO> {
        val cryptos =
            cryptoRepository.findBySymbolOrderByPricingHourAsc(symbol).ifEmpty { throw CryptoDoesntExistException() }
        val cryptosDTO: MutableList<CryptoDTO> = mutableListOf()
        cryptos.forEach {
            cryptosDTO.add(CryptoDTO.fromModel(it))
        }
        return cryptosDTO
    }

}
