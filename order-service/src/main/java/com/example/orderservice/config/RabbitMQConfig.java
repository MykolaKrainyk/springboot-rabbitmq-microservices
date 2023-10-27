package com.example.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.order.name}")
    private String orderQueueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key.name}")
    private String orderRoutingKey;

    // spring bean for queue - order queue
    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueueName);
    }

    // spring bean for exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // spring bean for binding between exchange and queue using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(orderQueue())
                .to(exchange())
                .with(orderRoutingKey);
    }
    // message converter
    // configure RabbitTemplate
}
