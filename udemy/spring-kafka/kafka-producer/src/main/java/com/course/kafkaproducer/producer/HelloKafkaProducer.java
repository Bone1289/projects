package com.course.kafkaproducer.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public HelloKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendHello(String name) {
        kafkaTemplate.send("t_hello", "Hello" + name);
    }
}
