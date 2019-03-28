package com.example.demo.controller;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.JMSMessage;

@RestController
@RequestMapping("/publish")
public class MessageProducer {
	
	@Autowired
	JmsTemplate jmsTemplate;

	@Autowired
	Queue queue;
	
	
	@GetMapping("/{message}")
	public String publishString(@PathVariable("message") String message) {
		
		jmsTemplate.convertAndSend(queue, message);
		return "The message "+ message+" published successfully";
		
	}

	@PostMapping("/json/")
	public String publishJSON(@RequestBody JMSMessage jmsMessage) {
		
		jmsTemplate.convertAndSend(queue, jmsMessage);
		return "The message "+ jmsMessage +" published successfully";

	}

}
