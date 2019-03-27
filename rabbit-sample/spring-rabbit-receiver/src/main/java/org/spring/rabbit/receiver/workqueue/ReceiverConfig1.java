package org.spring.rabbit.receiver.workqueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiverConfig1 {

    @Bean
    public HelloReceiver1 receiver1() {
        return new HelloReceiver1();
    }


}