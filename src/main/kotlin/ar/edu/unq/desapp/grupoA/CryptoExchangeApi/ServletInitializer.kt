package ar.edu.unq.desapp.grupoA.CryptoExchangeApi

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

class ServletInitializer : SpringBootServletInitializer() {

	override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder {
		return application.sources(CryptoExchangeApiApplication::class.java)
	}

}
