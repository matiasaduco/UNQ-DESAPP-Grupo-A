package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import org.springframework.stereotype.Service

@Service
interface IntentionService {
    fun createIntention(intention: Intention): Intention
}
