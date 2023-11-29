package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class HttpSecurityConfig {

    @Autowired
    private lateinit var authentiactionProvider: AuthenticationProvider

    @Bean
    fun apiFilterChain(http: HttpSecurity, jwtAuthenticationFilter: JwtAuthenticationFilter): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .headers { it.frameOptions { it.disable() } }
            .authorizeHttpRequests {
                it
                    .requestMatchers(AntPathRequestMatcher("/transaction/**"))
                    .authenticated()
                    .requestMatchers(AntPathRequestMatcher("/intention", "GET"))
                    .permitAll()
                    .requestMatchers(AntPathRequestMatcher("/intention/**"))
                    .authenticated()
                    .anyRequest().permitAll()
            }
            .httpBasic { }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authentiactionProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)


        return http.build()
    }
}