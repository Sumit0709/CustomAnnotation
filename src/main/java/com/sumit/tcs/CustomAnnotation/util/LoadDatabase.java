package com.sumit.tcs.CustomAnnotation.util;

import com.sumit.tcs.CustomAnnotation.model.Message;
import com.sumit.tcs.CustomAnnotation.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    MessageRepo messageRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading data in database.");
        Map<String, String> messages = new HashMap<>();
        messages.put("welcome", "Hello there, welcome to my java Spring Boot project.");
        messages.put("bye", "Thank you for vising! come again.");

        messages.forEach((String key, String value) -> {
//            System.out.println(key + " -> "+ value);
            messageRepository.save(new Message(key, value));
        });
    }
}
