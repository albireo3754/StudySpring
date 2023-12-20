package com.albireo3754.messagingredis

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicInteger

@Component
class Receiver(private val atomicCounter: AtomicInteger = AtomicInteger()) {

    fun receiveMessage(message: String) {
        logger.info("Received <$message>")
        atomicCounter.incrementAndGet()
    }

    fun handleMessage(message: String) {
        logger.info("handle <$message>")
        atomicCounter.incrementAndGet()
    }

    fun atomicCounter(): AtomicInteger {
        return atomicCounter
    }

    companion object {
        private val logger = LoggerFactory.getLogger(javaClass)
    }
}