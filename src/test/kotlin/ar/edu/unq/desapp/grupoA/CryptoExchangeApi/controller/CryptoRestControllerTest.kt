package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.controller

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.Configuration
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.BinancyProxyService
import org.hamcrest.CoreMatchers
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.format.DateTimeFormatter

@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = arrayOf(Configuration::class))
class CryptoRestControllerTest {


    @Autowired
    private lateinit var mvc : MockMvc

    @Autowired
    private lateinit var binancyProxyService: BinancyProxyService


    @Test
    fun getCryptoBySymbol(){

        var crypto = binancyProxyService.getAllCryptoCurrencyValues("").get(0)

        mvc.perform(get("/crypto/ALICEUSDT"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.symbol").value("ALICEUSDT"))
            .andExpect(jsonPath("$.price").value(10.0f))
            .andExpect(jsonPath("$.pricingTime").value(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(crypto.pricingTime)))
    }

    @Test
    fun getCryptoGivenWrongSymbol(){
        mvc.perform(get("/crypto/CRYPTO"))
            .andDo(print())
            .andExpect(status().isBadRequest)
            .andExpect(content().string(CoreMatchers.containsString("Nombre de cripto desconocido")))
    }

    @Test
    fun getCryptosPrices(){
        mvc.perform(get("/crypto/prices"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$",hasSize<Array<Any>>(2)))
            .andExpect(jsonPath("$[0].symbol").value("ALICEUSDT"))
            .andExpect(jsonPath("$[0].price").value(10.0f))
            .andExpect(jsonPath("$[1].symbol").value("MATICUSDT"))
            .andExpect(jsonPath("$[1].price").value(11.0f))

    }
}
