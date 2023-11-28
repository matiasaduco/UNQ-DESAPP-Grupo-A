package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.*
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.*
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.CryptoRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.IntentionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.DolarProxyService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.IntentionDTO
import jakarta.servlet.ServletContext
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class IntentionServiceImpl : IntentionService {

    @Autowired
    lateinit var intentionRepository: IntentionRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var cryptoRepository: CryptoRepository

    @Autowired
    lateinit var dolarProxyService: DolarProxyService

    @Autowired
    lateinit var tokenService: TokenService

    override fun createIntention(
        cryptoName: String,
        criptoNominalQuantity: Double,
        intentionCryptoPrice: Float,
        operation: IntentionType,
    ): IntentionDTO {
        return try {
            val email = tokenService.getEmail()

            val crypto = cryptoRepository.findById(CryptoId(cryptoName, LocalDateTime.now().hour))
                .orElseThrow { throw CryptoDoesntExistException() }

            if (!isPriceInside5Percent(intentionCryptoPrice, crypto)) {
                throw PriceOutOfRangeException("Precio de intención fuera de rango")
            }

            val user = userRepository.findFirstByEmail(email)
                .orElseThrow { throw UserDosentExists() }

            val intention = Intention(crypto, criptoNominalQuantity, intentionCryptoPrice, operation, user)
            intentionRepository.save(intention)
            val dolarPrice: DolarPrice = dolarProxyService.lastPrice
            val priceInArs: Double = (crypto.price * criptoNominalQuantity) * dolarPrice.v

            IntentionDTO.fromModel(intention, priceInArs)
        } catch (e: Exception) {
            throw IntentionCannotBeCreatedException(operation.toString())
        }
    }

    override fun getAllIntentions(): List<IntentionDTO> {

        val dolarPrice: DolarPrice = dolarProxyService.lastPrice
        val intentions: List<Intention> = intentionRepository.findAllNotFinished()
        val intentionsDTO: List<IntentionDTO> =
            intentions.map {
                val priceInArs: Double = it.crypto.price * it.cryptoNominalQuantity * dolarPrice.v
                IntentionDTO.fromModel(it, priceInArs)
            }
        return intentionsDTO
    }


    fun isPriceInside5Percent(intentionCryptoPrice: Float, crypto: Crypto): Boolean {
        val fivePercentage: Float = 5 * crypto.price / 100
        return intentionCryptoPrice >= crypto.price - fivePercentage && intentionCryptoPrice <= crypto.price + fivePercentage
    }
}
