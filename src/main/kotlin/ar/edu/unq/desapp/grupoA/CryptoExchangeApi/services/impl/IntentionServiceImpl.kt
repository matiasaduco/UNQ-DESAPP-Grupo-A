package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.DolarPrice
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.CryptoRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.IntentionDTO
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
        intentionCryptoPrice: Float,
        operation: IntentionType,
        userId: Int
    ): IntentionDTO {
        return try {
            val crypto = cryptoRepository.findById(cryptoName)
                .orElseThrow { throw Exception("Error al recuperar la Crypto ${cryptoName}.") }
            if (! isPriceInside5Percent(intentionCryptoPrice, crypto)){
                throw Exception("Precio de intención fuera de rango")
            }

            val user = userRepository.findById(userId)
                .orElseThrow { throw Exception("Error al recuperar el usuario con id ${userId}.") }

            val intention = Intention(crypto, criptoNominalQuantity, intentionCryptoPrice, operation, user)
            intentionRepository.save(intention)
            val dolarPrice : DolarPrice = DolarPrice.lastPrice()
            val priceInArs : Double = (crypto.price * criptoNominalQuantity) * dolarPrice.v

            return IntentionDTO.fromModel(intention, priceInArs)
        } catch (e: Exception) {
            throw Exception("Error al ingresar la intención de ${operation}.")
        }
    }

    override fun getAllIntentions(): List<IntentionDTO> {
         return try {
            val dolarPrice : DolarPrice = DolarPrice.lastPrice()
            val intentions: MutableIterable<Intention> = intentionRepository.findAllNotFinished()
            val intentionsDTO : List<IntentionDTO> = intentions.map {
                val priceInArs : Double = it.crypto.price * it.criptoNominalQuantity * dolarPrice.v
                IntentionDTO.fromModel(it, priceInArs)
            }
             intentionsDTO
        } catch (e: Exception) {
            throw Exception("Error al intentar recuperar las intenciones activas.")
        }
    }


    fun isPriceInside5Percent(intentionCryptoPrice: Float, crypto: Crypto): Boolean{
        val fivePercentage : Float = 5 * crypto.price / 100
        return intentionCryptoPrice >= crypto.price - fivePercentage && intentionCryptoPrice <= crypto.price + fivePercentage
    }
}
