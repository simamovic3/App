package com.tim4.app.account.rabbitmq;

import com.tim4.app.account.services.UserService;
import com.tim4.app.account.repositories.UsersRepository;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProducerConfiguration {

    @Bean
    public Exchange eventExchange(){
        return new TopicExchange("eventExchange");
    }

    @Bean
    public UserService userService(RabbitTemplate rabbitTemplate, Exchange eventExchange, UsersRepository userRepository){
        return new UserService(rabbitTemplate, eventExchange, userRepository);
    }
}
