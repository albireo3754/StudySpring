package com.albireo3754.messagingredis

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter

@SpringBootApplication
@Configuration
class MessagingRedisApplication {

	// ConnectionFactory는 자동주입
	@Bean
	fun container(connectionFactory: RedisConnectionFactory, listenerAdapter: MessageListenerAdapter): RedisMessageListenerContainer {
		val container = RedisMessageListenerContainer()
		container.setConnectionFactory(connectionFactory)
		container.addMessageListener(listenerAdapter, PatternTopic("chat"))
		return container
	}

	@Bean
	fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
		return MessageListenerAdapter(receiver)
	}

	@Bean
	fun template(connectionFactory: RedisConnectionFactory): StringRedisTemplate {
		return StringRedisTemplate(connectionFactory)
	}

	companion object {
		private val logger = LoggerFactory.getLogger(javaClass)
	}
}

fun main(args: Array<String>) {

	val ctx = runApplication<MessagingRedisApplication>(*args)
	val template = ctx.getBean(StringRedisTemplate::class.java)
	val receiver = ctx.getBean(Receiver::class.java)

	while (receiver.atomicCounter().compareAndSet(0, 0)) {
		LoggerFactory.getLogger(MessagingRedisApplication.javaClass).info("Sending message...")
		template.convertAndSend("chat", "Hello from Redis!")
		Thread.sleep(500L)
	}

	System.exit(0);
}
