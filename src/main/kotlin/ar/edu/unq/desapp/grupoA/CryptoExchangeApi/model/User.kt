package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model


class User(
    var name: String,
    var surname: String,
    var email: String,
    var address: String,
    var password: String,
    var cvu: String,
    var walletAddress: String,
) {
    var operations: Int = 0
    var reputation: Int = 0
    var id : Int? = null

    fun cancelTransaction(){
        this.reputation -= 20
        if (this.reputation < 0){
            this.reputation = 0
        }
    }

    fun acceptTransactionUnder30minutes(){
        this.reputation += 10
    }

    fun acceptTransactionOver30minutes(){
        this.reputation += 5
    }
}