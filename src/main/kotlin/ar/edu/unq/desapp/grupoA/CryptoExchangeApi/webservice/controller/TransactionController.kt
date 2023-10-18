package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.dto.TransactionActionDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/transaction")
class TransactionController(private val transactionService: TransactionService) {

    @PostMapping
    fun confirmTransaction(@RequestBody transactionActionDTO: TransactionActionDTO): ResponseEntity<Any>{

        return ResponseEntity.status(HttpStatus.OK).body("Ok")
    }

    @PostMapping
    fun cancelTransaction(@RequestBody transactionActionDTO: TransactionActionDTO): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.OK).body("Ok")
    }
}