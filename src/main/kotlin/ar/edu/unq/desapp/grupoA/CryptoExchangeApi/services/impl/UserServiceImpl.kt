package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Active
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.UserAlreadyExists
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.UserBodyIncorrectException
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.UserReport
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.IntentionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.TransactionRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.DolarProxyService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.time.LocalDateTime
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var intentionRepository: IntentionRepository

    @Autowired
    private lateinit var dolarProxyService: DolarProxyService


    override fun signup(user: User): User {
        userRepository.findFirstByEmail(user.email)
        if ( !userRepository.findFirstByEmail(user.email).isEmpty ){
            throw UserAlreadyExists()
        }
        else if (isValidateUser(user)) {
                userRepository.save(user)
                return user
        } else {
            throw UserBodyIncorrectException()
        }
    }

    private fun isValidateUser(user: User): Boolean {
        return hasAValidName(user.name) &&
                hasAValidName(user.surname) &&
                hasAValidEmail(user.email) &&
                hasAValidAddress(user.address) &&
                hasAValidPassword(user.userpassword) &&
                hasAValidCVU(user.cvu) &&
                hasAValidWalletAddress(user.walletAddress)
    }

    override fun login(): String {
        TODO("Not yet implemented")
    }

    override fun getUserReport(userId: Int, firstDate: LocalDateTime, lastDate : LocalDateTime): UserReport {
        val transactionsList = transactionRepository.findAllFinishedTransactionsByOwner(userId, firstDate,lastDate)
        var totalUSD = 0f
        transactionsList.forEach { totalUSD += it.intention.intentionCryptoPrice }

        val totalARG = dolarProxyService.getPriceInArs(totalUSD.toDouble()).toFloat()
        val activeList = mutableListOf<Active>()
        val activesIntentions = intentionRepository.findAllSaleIntentionsByUserID(userId)
        activesIntentions.forEach {
            activeList.add(
                Active(
                    it.crypto.symbol,
                    it.cryptoNominalQuantity,
                    it.intentionCryptoPrice,
                    dolarProxyService.getPriceInArs(it.intentionCryptoPrice * it.cryptoNominalQuantity).toFloat()
                )
            )
        }

        return UserReport(totalUSD, totalARG, activeList)
    }

    override fun getUsers(): List<UserDTO> {
        val users = userRepository.findAll()
        var usersDTO : MutableList<UserDTO> = mutableListOf()

        users.forEach {
            usersDTO.add( UserDTO.fromModel(it) )
        }

        return usersDTO
    }

    fun hasAValidName(name: String): Boolean {
        return name.length in 3..30
    }

    fun hasAValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".com")
    }

    fun hasAValidAddress(address: String): Boolean {
        return address.length >= 10 && address.length <= 30
    }

    fun hasAValidPassword(password: String): Boolean {
        val hasUpperCase = password.chars().anyMatch(Character::isUpperCase)

        val specialCharPattern: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
        val hasSpecialChar: Matcher = specialCharPattern.matcher(password)

        val hasLowerCase = password.chars().anyMatch(Character::isLowerCase)

        return hasUpperCase && hasSpecialChar.find() && hasLowerCase && password.length >= 6
    }

    fun hasAValidCVU(cvu: BigInteger): Boolean {
        return cvu.toString().length == 22
    }

    fun hasAValidWalletAddress(walletAddress: Int): Boolean {
        return walletAddress.toString().length == 8
    }
}
