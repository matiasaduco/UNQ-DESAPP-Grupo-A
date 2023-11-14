package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class HttpSecurityConfig {

    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain{
        http.invoke {
            authorizeRequests {
                authorize(anyRequest, permitAll)
            }
            csrf { disable() }
            headers { frameOptions { disable() } }
            httpBasic {  }
        }
        return http.build()
    }
}