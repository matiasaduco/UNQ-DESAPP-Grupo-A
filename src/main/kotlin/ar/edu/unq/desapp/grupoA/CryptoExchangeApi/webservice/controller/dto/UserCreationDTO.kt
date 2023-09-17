package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import java.math.BigInteger

class UserCreationDTO(
    val name: String,
    val surname: String,
    val email: String,
    val address: String,
    val password: String,
    val cvu: BigInteger,
    val walletAddress: Int,
) {

    fun toModel(): User {
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

    companion object {
        fun fromModel(user: User): UserCreationDTO {
            return UserCreationDTO(
                user.name,
                user.surname,
                user.email,
                user.address,
                user.password,
                user.cvu,
                user.walletAddress
            )
        }
    }

}
