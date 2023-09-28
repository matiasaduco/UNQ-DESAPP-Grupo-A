package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IntentionServiceImpl : IntentionService {

    @Autowired
    lateinit var intentionRepository: IntentionRepository

    override fun createIntention(intention: Intention): Intention {
        return try {
            intentionRepository.save(intention)
        } catch (e: Exception) {
            throw Exception("Error al ingresar la intención de ${intention.operation}.")
        }
    }

    override fun getAllIntentions(): MutableIterable<Intention> {
        return try {
            intentionRepository.findAllNotFinished()
        } catch (e: Exception) {
            throw Exception("Error al intentar recuperar las intenciones activas.")
        }
    }

}
