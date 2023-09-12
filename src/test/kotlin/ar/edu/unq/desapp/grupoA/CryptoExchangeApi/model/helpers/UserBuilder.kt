package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User

class UserBuilder() {
    var name: String  = "no name"
    var surname: String = "no surname"
    var email: String = "no email"
    var address: String = "no address"
    var password: String = "no password"
    var cvu: String = "no cvu"
    var walletAddress: String = "no wallet address"

    fun aPerson(): UserBuilder{ return UserBuilder() }

    fun build() : User{
        val user = User(name,surname,email,address,password,cvu,walletAddress)
        return user
    }

    fun withName(aName:String) = apply{ this.name = aName }

    fun withSurname(aSurname: String)= apply{this.surname = aSurname }

    fun withEmail(aEmail: String) = apply{ this.email = aEmail }

    fun withAddress(aAddress : String) = apply{ this.address = aAddress }

    fun withPassword(aPassword: String) = apply{ this.password = aPassword }

    fun withCVU(aCVU: String)= apply{ this.cvu = aCVU }

    fun withWalletAddress(aWalletAddress: String)= apply{ this.walletAddress = aWalletAddress }
}