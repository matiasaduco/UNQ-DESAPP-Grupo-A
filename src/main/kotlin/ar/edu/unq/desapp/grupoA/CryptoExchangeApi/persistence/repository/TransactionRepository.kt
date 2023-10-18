package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: CrudRepository<Transaction, Int> {
}