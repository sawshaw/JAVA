package org.spring.rabbit.receiver.workqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
@RabbitListener(queues = "hello")
public class HelloReceiver1 {
	@RabbitHandler
	public void receive(String in) {
		System.out.println(" [x] receiver1 Received '" + in + "'");
	}
}