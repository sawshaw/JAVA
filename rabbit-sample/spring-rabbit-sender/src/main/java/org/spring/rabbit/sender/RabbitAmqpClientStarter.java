package org.spring.rabbit.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitAmqpClientStarter {

	public static void main(String[] args) {
		SpringApplication.run(RabbitAmqpClientStarter.class, args);
	}
}