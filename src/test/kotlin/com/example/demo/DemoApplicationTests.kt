package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.Queue

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private lateinit var messageProducer: MessageProducer

    @Autowired
    private lateinit var messageStore: Queue<Message>

    @Test
    fun `should consume message from my-topic`() {
        val message = Message("Hello World!")
        messageProducer.send(message)

        val messageReceived = messageStore.poll()

        assertThat(messageReceived).isEqualTo(message)
    }

}
