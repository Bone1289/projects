package com.course.kafkaproducer;

import com.course.kafkaproducer.producer.HelloKafkaProducer;
import com.course.kafkaproducer.producer.KafkaKeyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    //    private final HelloKafkaProducer helloKafkaProducer;
    @Autowired
    private KafkaKeyProducer kafkaKeyProducer;
//    public KafkaProducerApplication(HelloKafkaProducer helloKafkaProducer) {
//        this.helloKafkaProducer = helloKafkaProducer;
//    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        helloKafkaProducer.sendHello("Bogdan " + Math.random());
        for (int i = 0; i < 30; i++) {
            var remainder = (i % 4);
            var keyValue = "key_" + remainder;
            var dataValue = "data_" + i;
            kafkaKeyProducer.send(keyValue, dataValue);
        }
    }
}
