package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.UserReport
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.UserService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.UserCreationDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.UserDTO
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.dto.fromDayTo
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.ZoneId

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @Operation(summary = "Registrar un usuario")
    @PostMapping
    fun signup(@RequestBody userDTO: UserCreationDTO): ResponseEntity<Any> {
            val user = userDTO.toModel()
            val userCreationDTO = UserDTO.fromModel(userService.signup(user))
            return ResponseEntity.status(HttpStatus.OK).body(userCreationDTO)
    }
    @Operation(summary = "Obtener todos los usuarios registrados")
    @GetMapping()
    fun getUsers(): ResponseEntity<Any> {
        val users = userService.getUsers()
        return ResponseEntity.status(HttpStatus.OK).body(users)
    }
    @Operation(summary = "Obtener el reporte del usuario dado")
    @GetMapping("/report/{userId}")
    fun getUserReport(@PathVariable userId: Int,@RequestBody fromDayTo: fromDayTo): ResponseEntity<Any> {
        val from = fromDayTo.from.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        val to = fromDayTo.to.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
        var report = userService.getUserReport(userId, from, to)
        return ResponseEntity.status(HttpStatus.OK).body(report)
    }
}
