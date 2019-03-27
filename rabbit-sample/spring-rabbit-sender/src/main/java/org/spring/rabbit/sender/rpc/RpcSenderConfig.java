package org.spring.rabbit.sender.rpc;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.rabbitmq.client.RpcClient;
@EnableScheduling
@Configuration
public class RpcSenderConfig {
	private static class ClientConfig {
		@Bean
		public DirectExchange exchange() {
			return new DirectExchange("tut.rpc");
		}
	}
	 private static class ServerConfig {
		@Bean
		public Queue queue1() {
			return new Queue("tut.rpc.requests");
		}
		
		/*
		 * @Bean public DirectExchange exchange() { return new
		 * DirectExchange("tut.rpc"); }
		 */
		@Bean
		public Binding binding(DirectExchange exchange, Queue queue1) {
			return BindingBuilder.bind(queue1).to(exchange).with("rpc");
		}
	 }

	@Bean
	public RpcSenderClient client() {
		return new RpcSenderClient();
	}

}
