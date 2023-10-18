package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class Transaction(
    @JoinColumn(name = "idIntention", nullable = false)
    @ManyToOne
    val intention: Intention,

    @OneToOne
    val userInterested: User
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null
    var createdAt: LocalDateTime = LocalDateTime.now()
    var transactionState: TransactionState = TransactionState.WAITING
}

enum class TransactionState {
    WAITING, TRANSFERED, CONFIRMED, CANCELED, FINISHED
}
