package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/crypto")
class CryptoController(private val cryptoService: CryptoService) {

    @GetMapping("/{cryptoSymbol}")
    fun getCryptoPrice(@PathVariable cryptoSymbol : String): Crypto{
        return cryptoService.getCryptoPrice(cryptoSymbol)
    }

    @GetMapping("/prices")
    fun getCryptosPrices() : List<Crypto>{
        return cryptoService.getCryptosPrice()
    }
}