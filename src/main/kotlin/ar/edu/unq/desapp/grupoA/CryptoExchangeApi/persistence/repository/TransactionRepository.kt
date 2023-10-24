package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.TransactionState
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface TransactionRepository : CrudRepository<Transaction, Int> {
    @Query(value = "SELECT t FROM Transaction t WHERE (t.intention.user.id = :userId OR t.userInterested.id = :userId) AND (t.createdAt BETWEEN :firstDate AND :lastDate) AND t.transactionState = 3")
    fun findAllFinishedTransactionsByOwner(userId: Int, firstDate: LocalDateTime, lastDate: LocalDateTime): List<Transaction>



}