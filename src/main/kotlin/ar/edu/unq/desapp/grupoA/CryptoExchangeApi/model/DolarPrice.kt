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
class DolarPrice (
    var d: LocalDate,
    val v: Double) {


    override fun toString(): String {
        return "date: " + this.d + ", price: " + this.v
    }

    companion object{
        fun lastPrice() : DolarPrice{
            var restTemplate : RestTemplate = RestTemplate()
            var headers : HttpHeaders = HttpHeaders()
            headers.set("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3Mjg3NjcyMTEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJsdWtzcGlvam9zb0BnbWFpbC5jb20ifQ.8_85V4p5IstbOFtco7dpg8h46m42ZwAh5Q0kx023MPZIWaUzS532T4F_cYZU0Z8yryP7TKCuNdBdZFj-fzl5vw")

            var entity : HttpEntity<Void> = HttpEntity<Void>(headers)
            var url : String = "https://api.estadisticasbcra.com/usd"

            var response : ResponseEntity<Array<DolarPrice>> = restTemplate.exchange(url, HttpMethod.GET, entity, Array<DolarPrice>::class.java)
            var prices : Array<DolarPrice> = response.body!!

            return prices.last()
        }
    }

}