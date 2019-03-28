package com.example.demo.controller;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.demo.model.JMSMessage;

@Component
public class MessageConsumer {

  /*  @JmsListener(destination = "Mithun.Queue")
    public void consumeString(String message) {
        System.out.println("Received Message: " + message);
    }
    */
    
    @JmsListener(destination = "Mithun.Queue")
    public void consumeJSON(JMSMessage jmsMessage) {
        System.out.println("Received Message: " + jmsMessage.toString());
    }
    
}
