package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User

interface UserService {
    fun signup(user: User): User
    fun login(): String
    fun getUserReport(id: Int): User
     fun getUsers(): List<UserDTO>
}