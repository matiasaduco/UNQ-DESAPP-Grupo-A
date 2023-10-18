package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.TransactionService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/transaction")
class TransactionController(private val transactionService: TransactionService) {

}