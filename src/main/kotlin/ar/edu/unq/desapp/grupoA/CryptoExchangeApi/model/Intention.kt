package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Intention(
    @ManyToOne(fetch = FetchType.EAGER)
    val crypto: Crypto,
    val criptoNominalQuantity: Int,
    val intentionPrice: Float,
    val operation: IntentionType,
    @ManyToOne(fetch = FetchType.EAGER)
    val user: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    val creationDate: LocalDate = LocalDate.now()
}

enum class IntentionType {
    BUY, SALE
}
