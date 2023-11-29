package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.CryptoDTO
import org.springframework.stereotype.Service

@Service
interface CryptoService {
    fun getCryptosPrice(): List<CryptoDTO>

    fun getCryptoPrice(symbol: String): CryptoDTO

    fun getCryptoDayPrice(symbol: String): List<CryptoDTO>
}
