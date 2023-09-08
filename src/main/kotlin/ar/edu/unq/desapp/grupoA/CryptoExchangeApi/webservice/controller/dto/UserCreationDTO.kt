package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User

class UserCreationDTO(
    val name: String,
    val surname: String,
    val email: String,
    val address : String,
    val password: String,
    val cvu: String,
    val walletAddress: String,
){


    fun toModel(): User{
        return User(
            name = this.name,
            surname = this.surname,
            email = this.email,
            address = this.address,
            password = this.password,
            cvu = this.cvu,
            walletAddress = this.walletAddress
        )
    }
}