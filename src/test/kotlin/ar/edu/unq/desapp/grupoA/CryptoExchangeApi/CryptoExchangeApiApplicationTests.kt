package ar.edu.unq.desapp.grupoA.CryptoExchangeApi

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = arrayOf(Configuration::class))
class CryptoExchangeApiApplicationTests {

	@Test
	fun contextLoads() {
	}

}
