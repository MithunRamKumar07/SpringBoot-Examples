package com.example.demo.config;

import java.util.ArrayList;
import java.util.Arrays;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class ActiveMQConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue("Mithun.Queue");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
    	
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setTrustedPackages(new ArrayList(Arrays.asList("com.example.demo.controller,com.example.demo.model".split(","))));
        factory.setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
    	
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
