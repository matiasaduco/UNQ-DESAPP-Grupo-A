package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.UserCreationDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun signup(@RequestBody userDTO: UserCreationDTO): ResponseEntity<Any> {
        return try {
            val user = userDTO.toModel()
            val userDTO = UserCreationDTO.fromModel(userService.signup(user))
            ResponseEntity.status(HttpStatus.OK).body(userDTO)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e)
        }
    }

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: Int): String {
        return "Soy un usuario, creeme."
    }

}
