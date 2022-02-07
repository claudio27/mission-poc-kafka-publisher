package com.example.kafkapublisher.consumer;

import com.example.kafkapublisher.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerController {

    List<String> messages = new ArrayList<>();
    User userFromTopic = null;

    @GetMapping("/consumeStringMessages")
    public List<String> consumeMessages() {
        return messages;
    }

    @GetMapping("/consumeJsonMessages")
    public User consumeJsonMessages() {
        return userFromTopic;
    }

    @KafkaListener(groupId = "group-text", topics = "quickstart-events", containerFactory = "kafkaListenerContainerFactory")
    public List<String> getMessagesFromTopic(String data) {
        messages.add(data);
        return messages;
    }

    @KafkaListener(groupId = "group-json", topics = "quickstart-events", containerFactory = "userKafkaListenerContainerFactory")
    public User getJsonMessagesFromTopic(User user) {
        userFromTopic = user;
        return userFromTopic;
    }
}
