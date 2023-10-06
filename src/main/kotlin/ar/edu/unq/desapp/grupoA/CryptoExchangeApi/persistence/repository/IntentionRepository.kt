package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IntentionRepository : CrudRepository<Intention, Int> {
    @Query(value = "SELECT i FROM Intention i WHERE NOT i.isFinished")
    fun findAllNotFinished(): MutableIterable<Intention>
}