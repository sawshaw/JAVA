package org.spring.rabbit.sender.hello;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SenderConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }
    @Bean
    public HelloSender sender() {
        return new HelloSender();
    }


}