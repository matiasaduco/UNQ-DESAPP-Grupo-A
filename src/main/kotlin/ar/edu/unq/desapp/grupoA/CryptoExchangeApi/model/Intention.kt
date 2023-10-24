package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Intention(
    @ManyToOne(fetch = FetchType.EAGER)
    val crypto: Crypto,
    val cryptoNominalQuantity: Double,
    val intentionCryptoPrice: Float,
    val operation: IntentionType,
    @ManyToOne(fetch = FetchType.EAGER)
    val user: User
) {

    fun canBeConfirmed(): Boolean {
        return if (operation == IntentionType.BUY) {
            crypto.price <= intentionCryptoPrice
        } else {
            crypto.price >= intentionCryptoPrice
        }
    }

    fun addressToSend(): String {
        return if (operation == IntentionType.BUY) {
            user.walletAddress.toString()
        } else {
            user.cvu.toString()
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    val creationDate: LocalDateTime = LocalDateTime.now()
    var isFinished: Boolean = false

}

enum class IntentionType {
    BUY, SALE
}
