package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.UserReport

interface UserService {
    fun signup(user: User): User
    fun login(): String
    fun getUserReport(userId: Int): UserReport
}
