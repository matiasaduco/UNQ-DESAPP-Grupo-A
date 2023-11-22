package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class UserTest {

    @Test
    fun userHasAName(){
        val name: String = "Lucas"
        val user = UserBuilder().withName(name).withName("Lucas")

        Assertions.assertEquals(user.name, name)
    }

    @Test
    fun userHasASurname(){
        val surname: String = "Alvarez"
        val user = UserBuilder().withSurname(surname).build()

        Assertions.assertEquals(user.surname, surname)
    }

    @Test
    fun userHasAEmail(){
        val email : String = "alvarezlucas@gmail.com"
        val user = UserBuilder().withEmail(email).build()

        Assertions.assertEquals(user.email, email)
    }

    @Test
    fun userHasAAddress(){
        val address : String = "Florencio Varela, Chascomus 1032"
        val user = UserBuilder().withAddress(address).build()

        Assertions.assertEquals(user.address, address)
    }

    @Test
    fun userHasAPassword(){
        val password : String = "eq4LR<5jv5jN"
        val user = UserBuilder().withPassword(password).build()

        Assertions.assertEquals(user.userpassword, password)
    }

    @Test
    fun userHasACVU(){
        val cvu : BigInteger = BigInteger("3102059301928473829192")
        val user = UserBuilder().withCVU(cvu).build()

        Assertions.assertEquals(user.cvu, cvu)
    }

    @Test
    fun userHasAWalletAddress(){
        val walletAddress : Int = 10492928
        val user = UserBuilder().withWalletAddress(walletAddress).build()

        Assertions.assertEquals(user.walletAddress, walletAddress)
    }

    @Test
    fun userStartsWith0Reputation(){
        val user = UserBuilder().build()

        Assertions.assertEquals(user.reputationPoints, 0)
    }

    @Test fun userStartsWith0Operations(){
        val user = UserBuilder().build()

        Assertions.assertEquals(user.operations, 0)
    }

    @Test
    fun whenUserAcceptsTransactionUnder30MinutesHisReputationIncreses(){
        val user : User = UserBuilder().build()
        val reputation : Int = user.reputationPoints
        user.acceptTransactionUnder30minutes()

        Assertions.assertEquals(user.reputationPoints, reputation + 10)
    }

    @Test
    fun whenUserAcceptsTransactionOver30MinutesHisReputationIncreses(){
        val user : User = UserBuilder().build()
        val reputation : Int = user.reputationPoints
        user.acceptTransactionOver30minutes()

        Assertions.assertEquals(user.reputationPoints, reputation + 5)
    }

    @Test
    fun whenUserCancelsTransactionHisReputationDecreases(){
        val user : User = UserBuilder().build()
        user.reputationPoints = 50
        val reputation : Int = user.reputationPoints
        user.cancelTransaction()

        Assertions.assertEquals(user.reputationPoints, reputation - 20)
    }

    @Test
    fun whenUserCancelsTransactionHisReputationCantGoUnder0(){
        val user : User = UserBuilder().build()
        val reputation : Int = user.reputationPoints
        user.cancelTransaction()

        Assertions.assertEquals(user.reputationPoints, reputation)
    }

    @Test
    fun whenAUserAcceptsATransactionHisOperationsIncresases(){
        val user : User = UserBuilder().build()
        val operations = user.operations

        user.acceptTransactionUnder30minutes()

        Assertions.assertEquals(user.operations, operations + 1)
    }

    @Test
    fun getUserReputation(){
        val user : User = UserBuilder().build()
        user.acceptTransactionUnder30minutes()
        val reputation  = (10 / 1).toDouble() // 10 points over 1 operation

        Assertions.assertEquals(user.getReputation() , reputation.toString())
    }
}