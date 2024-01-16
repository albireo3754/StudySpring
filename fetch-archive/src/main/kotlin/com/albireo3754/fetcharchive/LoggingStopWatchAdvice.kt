package com.albireo3754.fetcharchive

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDateTime
import java.util.logging.Logger


@Aspect
@Component
class LoggingStopWatchAdvice {

    companion object {
        val logger: org.slf4j.Logger = LoggerFactory.getLogger(this::class.java)
    }

    // make pointcut which has wildcard directory with file name is LoggingStopWatch

    @Around("@annotation(com.albireo3754.fetcharchive.LoggingStopWatch)")
    fun atTarget(joinPoint: ProceedingJoinPoint): Any? {
        val startAt = LocalDateTime.now()
        println("Start At : $startAt")

        val proceed = joinPoint.proceed()

        val endAt = LocalDateTime.now()

//        logger.info("End At : $startAt")

        println("Logic Duration : ${Duration.between(startAt, endAt).toMillis()}ms")

        return proceed
    }
}