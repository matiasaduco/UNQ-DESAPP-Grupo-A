package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User

interface UserService {
    fun signin(): User
    fun login(): String
    fun getUserReport(id: Int): User
}