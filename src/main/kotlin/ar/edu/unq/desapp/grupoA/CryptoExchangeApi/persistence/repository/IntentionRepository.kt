package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IntentionRepository : CrudRepository<Intention, Int> {
}