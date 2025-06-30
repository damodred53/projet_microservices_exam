package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue creditQueue() {
        return new Queue("credit-queue", true);
    }

    @Bean
    public DirectExchange creditExchange() {
        return new DirectExchange("credit-exchange");
    }

    @Bean
    public Binding creditBinding(Queue creditQueue, DirectExchange creditExchange) {
        return BindingBuilder.bind(creditQueue).to(creditExchange).with("credit.created");
    }
}
