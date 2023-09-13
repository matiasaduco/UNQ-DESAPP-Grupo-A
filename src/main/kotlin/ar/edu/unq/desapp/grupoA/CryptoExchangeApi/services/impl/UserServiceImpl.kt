package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl: UserService {
    // Eliminar en cuanto tengamos implementación de H2
    var userRepository : MutableList<User> = mutableListOf()

    override fun signin(user: User): User {
        try {
            userRepository.add(user)
            return user
        }
        catch (exception: Exception) {
            throw Exception("Error al ingresar el usuario $user")
        }
    }

    override fun login(): String {
        TODO("Not yet implemented")
    }

    override fun getUserReport(id: Int): User {
        TODO("Not yet implemented")
    }
}