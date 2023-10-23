package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.UserCreationDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.UserDTO
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
            val userCreationDTO = UserDTO.fromModel(userService.signup(user))
            ResponseEntity.status(HttpStatus.OK).body(userCreationDTO)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping()
    fun getUser(): ResponseEntity<Any> {
        val users = userService.getUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }

    @GetMapping("/report/{userId}")
    fun getUserReport(@PathVariable userId: Int) = userService.getUserReport(userId)

}
