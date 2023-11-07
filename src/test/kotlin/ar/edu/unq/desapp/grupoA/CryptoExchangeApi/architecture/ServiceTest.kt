package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.jupiter.api.Test
import org.springframework.stereotype.Service

class ServiceTest {

    @Test
    fun allServicesAreInServicePackage(){
        var importedClasses : JavaClasses = ClassFileImporter().importPackages("ar.edu.unq.desapp.grupoA.CryptoExchangeApi")

        var rule : ArchRule = classes().that().areAnnotatedWith(Service::class.java)
            .should().resideInAPackage("ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services..")

        rule.check(importedClasses)
    }
}