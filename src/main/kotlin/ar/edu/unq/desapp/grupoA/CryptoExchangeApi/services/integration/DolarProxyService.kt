package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.DolarPrice
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
@Configuration
@EnableScheduling
@Service
class DolarProxyService {
    private var restTemplate: RestTemplate = RestTemplate()
    private var bcraApiUrl: String = "https://api.estadisticasbcra.com/usd"

    lateinit var lastPrice : DolarPrice

    @Scheduled(fixedDelay = 600000 )
    @PostConstruct
    fun getLastDolarPrice(){
        var headers : HttpHeaders = HttpHeaders()
        headers.set("Authorization", "BEARER eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3Mjk2NDg5NDEsInR5cGUiOiJleHRlcm5hbCIsInVzZXIiOiJob2xhQGdtYWlsLmNvbSJ9._3kjD8GOCUiIVU7dy7NfInZV4X1FGaQi5amcvue9aZ26ImeDmm5qhnPjw6GY_tAru4hidzBKrrCifsqxAPuWdw")

        var entity : HttpEntity<Void> = HttpEntity<Void>(headers)

        var response : ResponseEntity<Array<DolarPrice>> = restTemplate.exchange(bcraApiUrl, HttpMethod.GET, entity, Array<DolarPrice>::class.java)
        var prices : Array<DolarPrice> = response.body!!

        this.lastPrice = prices.last()
    }

    fun getLastPrice(): Double{
        return lastPrice.v
    }

    fun getPriceInArs(price: Double): Double{
        return price * getLastPrice()
    }
}