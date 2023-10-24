package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User


class UserDTO(
    val name: String,
    val numberOfOperations: Int,
    val reputation: String
){
    companion object{
        fun fromModel(user: User):UserDTO{
            return UserDTO(
                user.getFullname(),
                user.operations,
                user.getReputation()
            )
        }
    }
}