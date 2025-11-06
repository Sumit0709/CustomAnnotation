package com.sumit.tcs.CustomAnnotation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "message_table")
public class Message {

    @Id
    private String messageKey;

    private String messageValue;

    protected Message() {}

    public Message(String messageKey, String messageValue) {
        this.messageValue = messageValue;
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageValue() {
        return messageValue;
    }

    public void setMessageValue(String messageValue) {
        this.messageValue = messageValue;
    }
}
