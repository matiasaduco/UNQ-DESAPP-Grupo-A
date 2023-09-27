package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto

interface CryptoService {

    fun getCryptosPrice(): List<Crypto>

    fun getCryptoPrice(symbol: String): Crypto
}
