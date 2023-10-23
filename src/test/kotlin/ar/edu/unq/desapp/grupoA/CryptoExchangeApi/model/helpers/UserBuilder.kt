package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import java.math.BigInteger


class UserBuilder() {
    var name: String  = "no name"
    var surname: String = "no surname"
    var email: String = "no email"
    var address: String = "no address"
    var password: String = "no password"
    var cvu = BigInteger("1111111111111111111111")
    var walletAddress: Int = 111111111

    fun build() : User{
        val user = User(name,surname,email,address,password,cvu,walletAddress)
        return user
    }

    fun withName(aName:String) = apply{ this.name = aName }

    fun withSurname(aSurname: String)= apply{this.surname = aSurname }

    fun withEmail(aEmail: String) = apply{ this.email = aEmail }

    fun withAddress(aAddress : String) = apply{ this.address = aAddress }

    fun withPassword(aPassword: String) = apply{ this.password = aPassword }

    fun withCVU(aCVU: BigInteger)= apply{ this.cvu = aCVU }

    fun withWalletAddress(aWalletAddress: Int)= apply{ this.walletAddress = aWalletAddress }
}