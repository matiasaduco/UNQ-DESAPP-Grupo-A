package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/crypto")
class CryptoController(private val cryptoService: CryptoService) {

    @Operation(summary = "Obtener el precio de la crypto dada")
    @GetMapping("/{cryptoSymbol}")
    fun getCryptoPrice(@PathVariable cryptoSymbol: String): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.OK).body(cryptoService.getCryptoPrice(cryptoSymbol))

    @Operation(summary = "Obtener el precio de todas las cryptos")
    @GetMapping("/prices")
    fun getCryptosPrices(): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.OK).body(cryptoService.getCryptosPrice())

    @Operation(summary = "Obtener el precio de las ultimas 24hs de la crypto dada")
    @GetMapping("/{cryptoSymbol}/day")
    fun getCryptoDailyPrice(@PathVariable cryptoSymbol: String): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.OK).body(cryptoService.getCryptoDayPrice(cryptoSymbol))

}
