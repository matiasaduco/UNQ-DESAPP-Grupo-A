package ar.edu.unq.desapp.grupoA.CryptoExchangeApi

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Aspect
@Component
@Order(0)
class LogInfoAspectCustomPoincut {

    var logger : Logger = LoggerFactory.getLogger(LogInfoAspectCustomPoincut::class.java)

    @Pointcut("execution(* ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller..*(..)) && ! execution(* ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.exceptionHandler..*(..))")
    fun methodsStarterServicePointcut(){}

    @Pointcut("execution(* ar.edu.unq.desapp.grupoA.CryptoExchangeApi.webservice.controller.exceptionHandler..*(..))")
    fun errorExceptionPointcut(){}


    @Around("methodsStarterServicePointcut()")
    fun logExecutionTimeAnnotation(joinPoint: ProceedingJoinPoint): Any? {

        var start : Long = System.currentTimeMillis()


        var methodSig : MethodSignature = joinPoint.signature as MethodSignature
        var params = methodSig.parameterNames

        var arguments = joinPoint.args
        var paramsAndValues = "/////// Parameters: "

        for (i in params.indices){
            paramsAndValues += params.get(i) + " = " + arguments.get(i) + " "
        }
        logger.info("/////// Timestamp: "+ start + " /////");
        logger.info("/////// Inside " + joinPoint.signature.name + "() method")
        logger.info(paramsAndValues)

        var proceed : Any = joinPoint.proceed()
        var executionTime = System.currentTimeMillis() - start

        logger.info("/////// LogExecutionTimeAspectAnnotation - " + joinPoint.signature + " executed in " + executionTime + "ms ")
        return proceed
    }

    @Around("errorExceptionPointcut()")
    fun logExecutionErrorInfo(joinPoint: ProceedingJoinPoint): Any? {
        var argument = joinPoint.args
        logger.error("Unexpected error", argument.get(0))

        var proceed: Any = joinPoint.proceed()
        return proceed
    }
}