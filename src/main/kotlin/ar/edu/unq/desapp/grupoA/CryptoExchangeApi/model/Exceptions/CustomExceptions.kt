package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Exceptions

class UserBodyIncorrectException() : Exception("User body field are incorrect")

class UserAlreadyExists(email: String) : Exception("Error al ingresar el usuario " + email + " credenciales existentes")

class UserDosentExists() : Exception("Usuario no encontrado")

class CryptoDoesntExistException() : Exception("Nombre de cripto desconocido")

class PriceOutOfRangeException(message: String) : Exception(message)

class IntentionCannotBeCreatedException(operation: String) :
    Exception("Error al ingresar la intención de ${operation}.")

class IntentionCannotBeFoundException(intentionID: Int) : Exception("Error al recuperar la intención $intentionID")

class TransactionCannotAdvance(message: String) : Exception(message)

class TransactionCannotBeFound(transactionId: Int) : Exception("Transacción $transactionId no encontrada")

class loginErrorException() : Exception("Usuario o contraseña erroneos")
