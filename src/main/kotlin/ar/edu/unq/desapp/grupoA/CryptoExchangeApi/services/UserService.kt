package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.UserReport
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.UserDTO
import java.time.LocalDateTime

interface UserService {
    fun signup(user: User): User
    fun login(): String
    fun getUserReport(userId: Int, firstDate: LocalDateTime, lastDate : LocalDateTime): UserReport
     fun getUsers(): List<UserDTO>
}