package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.UserBodyIncorrectException
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.UserReport
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigInteger
import java.util.regex.Matcher
import java.util.regex.Pattern

@Service
@Transactional
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun signup(user: User): User {
        if (isValidateUser(user)) {
            return try {
                userRepository.save(user)
                user
            } catch (exception: Exception) {
                throw Exception("Error al ingresar el usuario $user")
            }
        } else {
            throw UserBodyIncorrectException()
        }
    }

    private fun isValidateUser(user: User): Boolean {
        return hasAValidName(user.name) &&
                hasAValidName(user.surname) &&
                hasAValidEmail(user.email) &&
                hasAValidAddress(user.address) &&
                hasAValidPassword(user.password) &&
                hasAValidCVU(user.cvu) &&
                hasAValidWalletAddress(user.walletAddress)
    }

    override fun login(): String {
        TODO("Not yet implemented")
    }

    override fun getUserReport(id: Int): UserReport {
        TODO("Not yet implemented")
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
