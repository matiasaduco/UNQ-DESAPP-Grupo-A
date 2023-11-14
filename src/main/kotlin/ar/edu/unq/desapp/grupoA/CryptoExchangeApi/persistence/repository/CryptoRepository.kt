package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.persistence.repository

import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.Crypto
import ar.edu.unq.desapp.grupoA.CryptoExchangeApi.model.CryptoId
import org.springframework.data.repository.CrudRepository

interface CryptoRepository : CrudRepository<Crypto, CryptoId> {}
