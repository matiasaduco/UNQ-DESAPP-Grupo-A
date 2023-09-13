package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers.UserBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

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

        Assertions.assertEquals(user.password, password)
    }


    @Test
    fun userHasACVU(){
        val cvu : String = "3102059301928473829192"
        val user = UserBuilder().withCVU(cvu).build()

        Assertions.assertEquals(user.cvu, cvu)
    }

    @Test
    fun userHasAWalletAddress(){
        val walletAddress : String = "10492928"
        val user = UserBuilder().withWalletAddress(walletAddress).build()

        Assertions.assertEquals(user.walletAddress, walletAddress)
    }

    @Test
    fun userStartsWith0Reputation(){
        val user = UserBuilder().build()

        Assertions.assertEquals(user.reputation, 0)
    }

    @Test fun userStartsWith0Operations(){
        val user = UserBuilder().build()

        Assertions.assertEquals(user.operations, 0)
    }

    @Test
    fun whenUserAcceptsTransactionUnder30MinutesHisReputationIncreses(){
        val user : User = UserBuilder().build()
        val reputation : Int = user.reputation
        user.acceptTransactionUnder30minutes()

        Assertions.assertEquals(user.reputation, reputation + 10)
    }

    @Test
    fun whenUserAcceptsTransactionOver30MinutesHisReputationIncreses(){
        val user : User = UserBuilder().build()
        val reputation : Int = user.reputation
        user.acceptTransactionOver30minutes()

        Assertions.assertEquals(user.reputation, reputation + 5)
    }

    @Test
    fun whenUserCancelsTransactionHisReputationDecreases(){
        val user : User = UserBuilder().build()
        user.reputation = 50
        val reputation : Int = user.reputation
        user.cancelTransaction()

        Assertions.assertEquals(user.reputation, reputation - 20)
    }

    @Test
    fun whenUserCancelsTransactionHisReputationCantGoUnder0(){
        val user : User = UserBuilder().build()
        val reputation : Int = user.reputation
        user.cancelTransaction()

        Assertions.assertEquals(user.reputation, reputation)
    }
}