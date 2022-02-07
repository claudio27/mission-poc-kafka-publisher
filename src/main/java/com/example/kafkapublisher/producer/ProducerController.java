package com.example.kafkapublisher.producer;

import com.example.kafkapublisher.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ProducerController {

    private static final String TOPIC_NAME = "quickstart-events";

    @Autowired
    private KafkaTemplate<String, Object> template;

    @GetMapping("/publish/{name}")
    public String publishMessage(@PathVariable String name) {
        template.send(TOPIC_NAME, "Hola nuevo mensaje de " + name);
        return "Data published at " + LocalDateTime.now();
    }

    @GetMapping("/publishJson")
    public String publishMessage() {
        User user = new User(123, "NewUserName", new String[] {"Santiago", "Chile"});
        template.send(TOPIC_NAME, user);
        return "Data published at " + LocalDateTime.now();
    }
}
