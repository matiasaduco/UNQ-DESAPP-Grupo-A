package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class Transaction(
    @OneToOne
    val intention: Intention,

    @ManyToOne
    val userInterested: User
) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var createdAt: LocalDateTime = LocalDateTime.now()
    var transactionState: TransactionState = TransactionState.WAITING
    val address: String
        get() {
            return if (intention.operation == IntentionType.BUY) {
                intention.user.walletAddress.toString()
            } else {
                intention.user.cvu.toString()
            }
        }


    fun getAction(): String {
        return if (intention.operation == IntentionType.BUY) {
            ""
        } else {
            ""
        }
    }
}

enum class TransactionState {
    WAITING, TRANSFERED, CANCELED, FINISHED
}
