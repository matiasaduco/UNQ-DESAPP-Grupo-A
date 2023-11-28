package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import java.time.LocalDate

@JsonIgnoreProperties(ignoreUnknown = true)
class DolarPrice(
    var d: LocalDate,
    val v: Double
) {

    override fun toString(): String {
        return "date: " + this.d + ", price: " + this.v
    }

}
