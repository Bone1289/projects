package com.course.kafkaproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRates {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private int i = 0;

    private Logger logger = LoggerFactory.getLogger(FixedRates.class);

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {
        i++;
        logger.info("i is " + i);
        kafkaTemplate.send("t_fixed_rates", "Fixed rates " + i);
    }
}
