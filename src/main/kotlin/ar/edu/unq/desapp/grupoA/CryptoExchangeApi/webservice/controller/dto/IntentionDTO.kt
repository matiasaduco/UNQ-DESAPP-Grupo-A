package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Intention
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.IntentionType
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class IntentionDTO(
    val creationTime: String,
    val cryptoSymbol : String,
    val cryptoNominalQuantity: Int,
    val cryptoIntentionPrice: Float,
    val intentionPriceARS: Double,
    val operation: IntentionType,
    val name: String,
    val surname: String,
    val amountOfOperations: Int,
    val reputation : String
){

    companion object{
        fun fromModel(intention: Intention, intentionPriceARS: Double):IntentionDTO{
            return IntentionDTO(
                creationTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(intention.creationDate),
                cryptoSymbol = intention.crypto.symbol,
                cryptoNominalQuantity = intention.criptoNominalQuantity,
                cryptoIntentionPrice = intention.intentionCryptoPrice,
                intentionPriceARS = intentionPriceARS,
                operation = intention.operation,
                name = intention.user.name,
                surname = intention.user.surname,
                amountOfOperations = intention.user.operations,
                reputation = intention.user.getReputation()
                )
        }
    }
}