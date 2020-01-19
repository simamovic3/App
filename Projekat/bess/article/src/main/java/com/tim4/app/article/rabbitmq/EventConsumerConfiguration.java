package com.tim4.app.article.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConsumerConfiguration {

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("articleServiceQueue");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange eventExchange) {
        return BindingBuilder.bind(queue).to(eventExchange)
                .with("user.deleted");
    }

    @Bean
    public EventConsumer eventReceiver() {
        return new EventConsumer();
    }

    @Bean

    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(this.queue().getName());
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(EventConsumer receiver) {

        return new MessageListenerAdapter(receiver, "receive");

    }

}