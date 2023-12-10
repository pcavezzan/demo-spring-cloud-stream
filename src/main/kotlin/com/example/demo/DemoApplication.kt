package com.example.demo

import io.github.serpro69.kfaker.Faker
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.context.annotation.Bean
import java.util.LinkedList
import java.util.Queue
import java.util.function.Consumer

private val LOGGER = LoggerFactory.getLogger(DemoApplication::class.java)

@SpringBootApplication
class DemoApplication {

    @Bean
    fun messageStore(): Queue<Message> = LinkedList()

    @Bean
    fun consumer(messageStore: Queue<Message>): Consumer<Message> = Consumer { message ->
        LOGGER.info("Received: {}", message)
        messageStore.add(message)
    }

    @Bean
    fun messageProducer(streamBridge: StreamBridge): MessageProducer = MessageProducer(streamBridge)
}

data class Message(val content: String)

class MessageProducer(private val streamBridge: StreamBridge) {
    fun send(message: Message) {
        LOGGER.info("Sending: {}", message)
        streamBridge.send("producer", message)
    }
}

fun main(args: Array<String>) {
    val application = runApplication<DemoApplication>(*args)
    val faker = Faker()
    val messageProducer = application.getBean(MessageProducer::class.java)
    while (true) {
        messageProducer.send(Message("Hello, ${faker.name.name()}!"))
        Thread.sleep(2000)
    }
}
