package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.exceptionHandler

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserBodyIncorrectException::class)
    fun handleBadRequest(exception: UserBodyIncorrectException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(UserAlreadyExists::class)
    fun handleUserAlreadyExists(exception: UserAlreadyExists): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(UserDosentExists::class)
    fun handleUserDosentExist(exception: UserDosentExists): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(CryptoDoesntExistException::class)
    fun handleCryptoDosentExists(exception: CryptoDoesntExistException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(PriceOutOfRangeException::class)
    fun handlePriceOutOfRange(exception: CryptoDoesntExistException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(IntentionCannotBeCreatedException::class)
    fun handleIntentionCannotBeCreated(exception: IntentionCannotBeCreatedException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(IntentionCannotBeFoundException::class)
    fun handleIntentionCannotBeFound(exception: IntentionCannotBeFoundException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(TransactionCannotAdvance::class)
    fun handleTransactionCannotAdvance(exception: TransactionCannotAdvance): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.message)
    }

    @ExceptionHandler(TransactionCannotBeFound::class)
    fun handleTransactionCannotBeFound(exception: TransactionCannotBeFound): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
    }

    @ExceptionHandler(loginErrorException::class)
    fun handleLoginError(exception: loginErrorException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.message)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(exception: RuntimeException): ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.message)
    }
}