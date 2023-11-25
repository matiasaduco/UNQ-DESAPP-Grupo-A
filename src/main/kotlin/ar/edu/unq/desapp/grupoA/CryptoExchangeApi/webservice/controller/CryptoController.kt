package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/crypto")
class CryptoController(private val cryptoService: CryptoService) {

    @GetMapping("/{cryptoSymbol}")
    fun getCryptoPrice(@PathVariable cryptoSymbol: String): ResponseEntity<Any> {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cryptoService.getCryptoPrice(cryptoSymbol))
            }
        catch (e: Exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/prices")
    fun getCryptosPrices(): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(cryptoService.getCryptosPrice())
    }

}
