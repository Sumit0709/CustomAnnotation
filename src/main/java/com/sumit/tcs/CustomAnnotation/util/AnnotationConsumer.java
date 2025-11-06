package com.sumit.tcs.CustomAnnotation.util;

import com.sumit.tcs.CustomAnnotation.annotations.MyAnnotation.ContentValue;
import com.sumit.tcs.CustomAnnotation.model.Message;
import com.sumit.tcs.CustomAnnotation.repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
public class AnnotationConsumer {

    @Autowired
    MessageRepo messageRepository;

    private String getDetailedMessageFromDB(String key){
        Message message = messageRepository.findById(key).orElse(new Message(key, "Message not found for "+key));
        return message.getMessageValue();
    }

    private String getDetailedMessage(String key){
        if(key.equals("welcome")){
            return "This is welcome message";
        }
        else if(key.equals("bye")){
            return "This is bye message";
        }
        else{
            return "Default message";
        }
    }


    public void consumeContentValueAnnotation(Object obj) {
        System.out.println("Consuming content value annotation in :: "+ obj.getClass());
        Class<?> c = obj.getClass();

        Field[] declaredFields = c.getDeclaredFields();
        for(Field f: declaredFields){
//            System.out.println("Field :: "+ f.getName());
            if(f.isAnnotationPresent(ContentValue.class)){
                f.setAccessible(true);
                String key = f.getAnnotation(ContentValue.class).value();
//                System.out.println("value = "+ key);
                try {
                    f.set(obj, getDetailedMessageFromDB(key));
                } catch (IllegalAccessException e) {
                    System.out.println("Invalid request :: IllegalAccessException - " + e.getMessage());
                }
            }
//            System.out.println();
        }

//        System.out.println(" ************************* ");

    }
}
