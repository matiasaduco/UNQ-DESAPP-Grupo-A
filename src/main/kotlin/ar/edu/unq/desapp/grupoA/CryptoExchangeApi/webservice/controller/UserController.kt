package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.UserCreationDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @PostMapping
    fun signin(@RequestBody userDTO: UserCreationDTO): UserCreationDTO {
        val user = userDTO.toModel()
        return UserCreationDTO.fromModel(userService.signin(user))
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Int) : String {
        return "Soy un usuario, creeme."
    }

}
