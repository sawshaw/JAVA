package org.spring.rabbit.receiver.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
@RabbitListener(queues = "hello")
public class HelloReceiver {
	@RabbitHandler
	public void receive(String in) {
		System.out.println(" [x] receiver  Received '" + in + "'");
	}
}