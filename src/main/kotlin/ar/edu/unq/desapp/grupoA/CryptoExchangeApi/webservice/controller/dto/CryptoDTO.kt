package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import java.time.format.DateTimeFormatter

class CryptoDTO (
    val symbol: String,
    val price: Float,
    val pricingTime: String
){

    companion object{
        fun fromModel(crypto: Crypto): CryptoDTO{
            return CryptoDTO(
                crypto.symbol,
                crypto.price,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(crypto.pricingTime)
            )
        }
    }
}