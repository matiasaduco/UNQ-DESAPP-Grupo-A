package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.CryptoRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.CryptoService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IntentionServiceImpl : IntentionService {

    @Autowired
    lateinit var intentionRepository: IntentionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var cryptoRepository: CryptoRepository

    override fun createIntention(
        cryptoName: String,
        criptoNominalQuantity: Int,
        intentionPriceInArs: Float,
        operation: IntentionType,
        userId: Int
    ): Intention {
        return try {
            val crypto = cryptoRepository.findById(cryptoName)
                .orElseThrow { throw Exception("Error al recuperar la Crypto ${cryptoName}.") }
            val user = userRepository.findById(userId)
                .orElseThrow { throw Exception("Error al recuperar el usuario con id ${userId}.") }
            val intention = Intention(crypto, criptoNominalQuantity, intentionPriceInArs, operation, user)
            intentionRepository.save(intention)
        } catch (e: Exception) {
            throw Exception("Error al ingresar la intención de ${operation}.")
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
