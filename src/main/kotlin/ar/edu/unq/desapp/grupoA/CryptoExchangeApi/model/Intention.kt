package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.*
import org.springframework.web.client.RestTemplate
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class Intention(
    @ManyToOne(fetch = FetchType.EAGER)
    val crypto: Crypto,
    val criptoNominalQuantity: Int,
    val intentionCryptoPrice : Float,
    val operation: IntentionType,
    @ManyToOne(fetch = FetchType.EAGER)
    val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    val creationDate: LocalDateTime = LocalDateTime.now()
    var isFinished: Boolean = false

}

enum class IntentionType {
    BUY, SALE
}
