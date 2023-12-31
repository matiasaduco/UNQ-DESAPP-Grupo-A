package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.service

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.Configuration
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl.UserServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigInteger


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = arrayOf(Configuration::class))
class UserServiceTest {

    @Autowired
    lateinit var userService : UserServiceImpl

    @Test
    fun checkThatNameIsValid(){
        val name : String = "Pedro"
        val result : Boolean = userService.hasAValidName(name)
        Assertions.assertTrue(result)
    }

    @Test
    fun checkThatNameIsNotValid(){
        val name : String = "@}+dadobalnáldw lqwe jop+dk p´dl as´dppddakdas"
        val result : Boolean = userService.hasAValidName(name)

        Assertions.assertFalse(result)
    }

    @Test
    fun checkThatEmailIsValid(){
        val email : String = "alvarezlucas@gmail.com"
        val result : Boolean = userService.hasAValidEmail(email)

        Assertions.assertTrue(result)
    }

    @Test
    fun checkThatEmailIsNotValidBecauseDosentContainsAtSign(){
        val email : String = "alvarezlucas.com"
        val result : Boolean = userService.hasAValidEmail(email)

        Assertions.assertFalse(result)
    }

    @Test
    fun checkThatEmailIsNotValidBecauseDosentContainsDotCom(){
        val email : String = "alvarezlucas@gmail"
        val result : Boolean = userService.hasAValidEmail(email)

        Assertions.assertFalse(result)
    }

    @Test
    fun checkThatAddressIsValid(){
        val address : String = "Chascomus 1041"
        val result : Boolean = userService.hasAValidAddress(address)

        Assertions.assertTrue(result)
    }

    @Test
    fun checkThatAddressIsNotValid(){
        val address : String = "Chascomus 1041, Florencio Varela"
        val result : Boolean = userService.hasAValidAddress(address)

        Assertions.assertFalse(result)
    }

    @Test
    fun checkThatPasswordIsValid(){
        val password : String = "Contraseña@"
        val result : Boolean = userService.hasAValidPassword(password)

        Assertions.assertTrue(result)

    }

    @Test
    fun CheckThatPasswordIsNotValidBecauseDosentContainsSpecialChar(){
        val password : String = "Contraseña"
        val result : Boolean = userService.hasAValidPassword(password)

        Assertions.assertFalse(result)
    }

    @Test
    fun CheckThatPasswordIsNotValidBecauseDosentContainsAUpperCase(){
        val password : String = "contraseña@"
        val result : Boolean = userService.hasAValidPassword(password)

        Assertions.assertFalse(result)
    }

    @Test
    fun CheckThatPasswordIsNotValidBecauseDosentContainsALowerCase(){
        val password : String = "CONTRASEÑA@"
        val result : Boolean = userService.hasAValidPassword(password)

        Assertions.assertFalse(result)
    }

    @Test
    fun CheckThatCVUIsValid(){
        val cvu : BigInteger = BigInteger("1234567891234567891234")
        val result : Boolean = userService.hasAValidCVU(cvu)

        Assertions.assertTrue(result)
    }

    @Test
    fun CheckThatCVUIsNotValid(){
        val cvu : BigInteger = BigInteger("12345")
        val result : Boolean = userService.hasAValidCVU(cvu)

        Assertions.assertFalse(result)
    }

    @Test
    fun checkThatWalletAddressIsValid(){
        val walletAddress : Int = 12345678
        val result : Boolean = userService.hasAValidWalletAddress(walletAddress)

        Assertions.assertTrue(result)
    }

    @Test
    fun checkThatWalletAddressIsNotValid(){
        val walletAddress : Int = 12345
        val result : Boolean = userService.hasAValidWalletAddress(walletAddress)

        Assertions.assertFalse(result)
    }
}