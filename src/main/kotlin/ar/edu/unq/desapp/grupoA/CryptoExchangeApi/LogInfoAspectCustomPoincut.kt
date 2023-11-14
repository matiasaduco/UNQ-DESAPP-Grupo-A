package ar.edu.unq.desapp.grupoA.CryptoExchangeApi

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.sql.Timestamp

@Aspect
@Component
@Order(0)
class LogInfoAspectCustomPoincut {

    var logger : Logger = LoggerFactory.getLogger(LogInfoAspectCustomPoincut::class.java)

    @Pointcut("execution(* ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller..*(..))")
    fun methodsStarterServicePointcut(){}

    @Before("methodsStarterServicePointcut()")
    fun beforeMethods(){
        var executionStart : Timestamp = Timestamp(System.currentTimeMillis())
        logger.info("/////// Timestamp: "+ executionStart + " /////");
    }

    @Around("methodsStarterServicePointcut()")
    fun logExecutionTimeAnnotation(joinPoint: ProceedingJoinPoint): Any? {

        var start : Long = System.currentTimeMillis()

        var proceed : Any = joinPoint.proceed()
        var executionTime = System.currentTimeMillis() - start

        logger.info("/////// LogExecutionTimeAspectAnnotation - " + joinPoint.getSignature() + " executed in " + executionTime + "ms ")
        return proceed
    }

}