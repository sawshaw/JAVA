package org.spring.rabbit.receiver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitAmqpServerStarter {
	public static void main(String[] args){
		SpringApplication.run(RabbitAmqpServerStarter.class, args);
	}
}