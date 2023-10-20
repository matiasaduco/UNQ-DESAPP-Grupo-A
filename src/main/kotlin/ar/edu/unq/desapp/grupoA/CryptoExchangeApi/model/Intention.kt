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
        if (operation == IntentionType.BUY){
            return crypto.price <= intentionCryptoPrice
        }else{
            return crypto.price >= intentionCryptoPrice
        }
    }

    fun addressToSend(): String {
        if (operation == IntentionType.BUY){
            return user.walletAddress.toString()
        }else{
            return user.cvu.toString()
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
