package com.example.kafkapublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {

    private static final String TOPIC_NAME = "quickstart-events";

    @Autowired
    private KafkaTemplate<String, Object> template;

    @GetMapping("/publish/{name}")
    public String publishMessage(@PathVariable String name) {
        template.send(TOPIC_NAME, "Hola nuevo mensaje de " + name);
        return "Data published at " + LocalDateTime.now();
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaPublisherApplication.class, args);
    }


}
