package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.User
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.UserCreationDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    var db : MutableList<User> = mutableListOf()
    @PostMapping
    fun sigin(@RequestBody userDTO: UserCreationDTO): String{
        var user = userDTO.toModel()
        db.add(user)
        return "Usuario ingresado"
    }

    @GetMapping
    fun getUser() : MutableList<User>{
        return db
    }

}

