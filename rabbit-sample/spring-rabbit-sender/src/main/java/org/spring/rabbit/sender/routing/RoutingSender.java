package org.spring.rabbit.sender.routing;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class RoutingSender {
	private final String[] keys = {"orange", "black", "green"};
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;
    AtomicInteger index = new AtomicInteger(0);
    //线程安全的全局变量
    AtomicInteger count = new AtomicInteger(0);
    

    //@Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void send() {
    	StringBuilder builder = new StringBuilder("Hello to ");
        if (this.index.incrementAndGet() == 3) {
            this.index.set(0);
        }
        String key = keys[this.index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.incrementAndGet());
        String message = builder.toString();
        template.convertAndSend(direct.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}