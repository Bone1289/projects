package com.course.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FixedRatesConsumer {
    private static final Logger log = LoggerFactory.getLogger(FixedRatesConsumer.class);

    @KafkaListener(topics = "t_fixed_rates")
    public void consume(String message) {
        log.info("Consuming: {}", message);
    }

}
