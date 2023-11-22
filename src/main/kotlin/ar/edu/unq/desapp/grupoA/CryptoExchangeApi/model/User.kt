package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.math.BigInteger

@Entity
@Table(name = "_USER")
class User(
    var name: String,
    var surname: String,
    @Column(unique = true)
    var email: String,
    var address: String,
    var userpassword: String,
    @Column(unique = true)
    var cvu: BigInteger,
    @Column(unique = true)
    var walletAddress: Int,
) : UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    var operations: Int = 0
    var reputationPoints: Int = 0

    fun getReputation(): String {
        if (this.operations == 0){
            return "Sin operaciones"
        }else{
            return "" + this.reputationPoints / this.operations.toDouble()
        }
    }

    fun cancelTransaction() {
        this.reputationPoints = (this.reputationPoints - 20).coerceAtLeast(0)
    }

    fun acceptTransactionUnder30minutes() {
        this.reputationPoints += 10
        this.operations += 1
    }

    fun acceptTransactionOver30minutes() {
        this.reputationPoints += 5
        this.operations += 1
    }

    fun getFullname(): String{
        return this.name + " " + this.surname
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}
