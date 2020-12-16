package com.example.urlshortenergateway.kafka.producer

import com.example.urlshortenergateway.kafka.messageType.UrlClickMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class Producer(val kafkaTemplate: KafkaTemplate<String, UrlClickMessage> ) {
    private val topic:String = "urlClick"

    fun sendMessage(message: UrlClickMessage) {
        kafkaTemplate.send("kotlin-events", message)
        println(message)
    }

}