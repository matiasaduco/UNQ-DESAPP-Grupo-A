package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.utils

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun myOpenAPI(): OpenAPI{
        var devServer : Server = Server()
        devServer.url = "crypto.exchange.dev-url"
        devServer.description = "Server URL in Development environment"

        var contact: Contact = Contact()
        contact.email = "crypto.exchange@crypto.exchange.com"
        contact.name= "crypto.exchange"
        contact.url= "https://www.crypto.exchange.com"

        var mitLicense : License = License().name("MIT License").url("https://cryptoExcange.com/licenses/mit/")

        var info : Info = Info()
            .title("Crypto Exchange API")
            .version("1.0")
            .contact(contact)
            .description("API de trading de criptomonedas")
            .termsOfService("https://www.cryptoexchange.com/terms")
            .license(mitLicense)

        return OpenAPI().info(info).servers(listOf(devServer))
    }

}
