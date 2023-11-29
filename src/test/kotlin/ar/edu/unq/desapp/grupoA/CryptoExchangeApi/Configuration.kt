package ar.edu.unq.desapp.grupoA.CryptoExchangeApi

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.helpers.CryptoBuilder
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository.UserRepository
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.impl.CustomUserDetailsService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.BinancyProxyService
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services.integration.DolarProxyService
import org.hamcrest.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

@TestConfiguration
class Configuration {



    @Bean
    @Primary
    fun binanceProxyService() : BinancyProxyService{
        val date = LocalDateTime.of(2023,10,12,LocalDateTime.now().hour,0)
        val crypto2 : Crypto = CryptoBuilder().withName("ALICEUSDT").withPrice(10.0f).withTime(date).build()
        val crypto3 : Crypto = CryptoBuilder().withName("MATICUSDT").withPrice(11.0f).withTime(date).build()


        var binancyProxyService: BinancyProxyService = mock(BinancyProxyService::class.java)

        Mockito.`when`(binancyProxyService.getAllCryptoCurrencyValues(anyString())).thenReturn(arrayOf(crypto2,crypto3))



        return binancyProxyService
    }

    @Bean
    @Primary
    fun dolarProxyService() : DolarProxyService{
        var dolarProxyService: DolarProxyService = mock(DolarProxyService::class.java)

        Mockito.`when`(dolarProxyService.getPriceInArs(anyDouble())).thenReturn(1000.0)
        return dolarProxyService
    }

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService =
        CustomUserDetailsService(userRepository)

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationProvider(userRepository: UserRepository): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also {
                it.setUserDetailsService(userDetailsService(userRepository))
                it.setPasswordEncoder(encoder())
            }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}