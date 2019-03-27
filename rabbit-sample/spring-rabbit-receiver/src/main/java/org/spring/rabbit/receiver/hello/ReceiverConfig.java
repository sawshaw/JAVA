package org.spring.rabbit.receiver.hello;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig {

    @Bean
    public Queue queue() {
        return new Queue("hello");
    }
    @Bean
    public HelloReceiver receiver() {
        return new HelloReceiver();
    }


}