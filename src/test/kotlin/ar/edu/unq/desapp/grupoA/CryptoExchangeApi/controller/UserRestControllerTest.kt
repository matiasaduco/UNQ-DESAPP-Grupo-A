package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.Configuration
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.hamcrest.CoreMatchers
import org.hamcrest.collection.IsCollectionWithSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import java.math.BigInteger
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime
import java.util.*

@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = arrayOf(Configuration::class))
class UserRestControllerTest {

    @Autowired
    private lateinit var mockMvc : MockMvc

    @Test
    fun singupSuccesfully(){
        var body = mapOf(
            "name" to "Lucas",
            "surname" to "Alvarez",
            "email" to "email@gmail.com",
            "address" to "Calle Falsa 123",
            "password" to "Contraseña@",
            "cvu" to BigInteger("1234567890123456789012"),
            "walletAddress" to 87654321)

        mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jacksonObjectMapper().writeValueAsString(body)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value("Lucas Alvarez"))
            .andExpect(jsonPath("$.numberOfOperations").value(0))
            .andExpect(jsonPath("$.reputation").value("Sin operaciones"))

    }

    @Test
    fun whenBodyIsIncorrectInSignup(){
        var body = mapOf(
            "name" to "Lucas",
            "surname" to "Alvarez",
            "email" to "hola",
            "address" to "Calle Falsa 123",
            "password" to "Contraseña@",
            "cvu" to BigInteger("1234567890123456789012"),
            "walletAddress" to 87654321)

        mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jacksonObjectMapper().writeValueAsString(body)))
            .andExpect(status().isBadRequest)
            .andExpect(content().string(CoreMatchers.containsString("User body field are incorrect")))

    }

    @Test
    fun whenCredentialAreAlreadyInUse(){
        var body = mapOf(
            "name" to "Lucas",
            "surname" to "Alvarez",
            "email" to "alvarez@gmail.com",
            "address" to "Calle Falsa 123",
            "password" to "Contraseña@",
            "cvu" to BigInteger("1234567890123456789012"),
            "walletAddress" to 87654321)

        //email "alvarez@gmail.com" ya se encuentra en uso

        mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jacksonObjectMapper().writeValueAsString(body)))
            .andExpect(status().isBadRequest)
            .andExpect(content().string(CoreMatchers.containsString("Error al ingresar el usuario Lucas Alvarez, credenciales existentes")))
    }

    @Test
    fun getAllUsers(){
        mockMvc.perform(get("/user"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$", IsCollectionWithSize.hasSize<Array<Any>>(10)))
            .andExpect(jsonPath("$[0].name").value("Lucas Alvarez"))
            .andExpect(jsonPath("$[0].numberOfOperations").value(0))
            .andExpect(jsonPath("$[0].reputation").value("Sin operaciones"))
            .andExpect(jsonPath("$[1].name").value("Matias Aduco"))
            .andExpect(jsonPath("$[1].numberOfOperations").value(0))
            .andExpect(jsonPath("$[1].reputation").value("Sin operaciones"))
    }

    @Test
    fun getUserReport(){
        var body = mapOf(
            "from" to Date(2023,10,10),
            "to" to Date(2023,10,30),
        )
        mockMvc.perform(get("/user/report/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jacksonObjectMapper().writeValueAsString(body)))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.totalUSD").value(0f))
            .andExpect(jsonPath("$.totalARG").value(0f))
            .andExpect(jsonPath("$.activeList", IsCollectionWithSize.hasSize<Array<Any>>(0)))

    }
}