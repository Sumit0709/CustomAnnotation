package com.sumit.tcs.CustomAnnotation.dao;


import com.sumit.tcs.CustomAnnotation.annotations.MyAnnotation.ContentValue;
import com.sumit.tcs.CustomAnnotation.util.AnnotationConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ByeMessage {

    @Autowired
    AnnotationConsumer ac;

    @ContentValue("bye")
    String message;

    @EventListener(ApplicationReadyEvent.class)
    public void consumeAnnotation(){
        ac.consumeContentValueAnnotation(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
