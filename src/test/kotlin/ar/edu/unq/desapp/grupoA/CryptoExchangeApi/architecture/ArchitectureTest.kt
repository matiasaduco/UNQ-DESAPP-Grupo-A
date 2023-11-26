package ar.edu.unq.desapp.grupoA.CryptoExchangeApi.architecture

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods
import com.tngtech.archunit.library.Architectures
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service

class ArchitectureTest {

    var importedClasses : JavaClasses = ClassFileImporter()
        .withImportOption( ImportOption.DoNotIncludeTests() )
        .importPackages("ar.edu.unq.desapp.grupoA.CryptoExchangeApi")
    @Test
    fun allServicesAreInServicePackage(){

        var rule : ArchRule = classes().that().areAnnotatedWith(Service::class.java)
            .should().resideInAPackage("ar.edu.unq.desapp.grupoA.CryptoExchangeApi.services..")

        rule.check(importedClasses)
    }

    @Test
    fun layerDependenciesAreRespected(){
        var rule : ArchRule = Architectures.layeredArchitecture().consideringAllDependencies()
            .layer("Controller").definedBy("..webservice.controller..")
            .layer("Service").definedBy("..services..")
            .layer("Persistence").definedBy("..persistence.repository..")

            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service")


        rule.check(importedClasses)
    }

    @Test
    fun allMethodsInControllerShouldReturnResponseEntity(){
        var rule : ArchRule = methods()
            .that().areDeclaredInClassesThat().resideInAPackage("..webservice.controller..")
            .should().haveRawReturnType(ResponseEntity::class.java)

        rule.check(importedClasses)
    }

}