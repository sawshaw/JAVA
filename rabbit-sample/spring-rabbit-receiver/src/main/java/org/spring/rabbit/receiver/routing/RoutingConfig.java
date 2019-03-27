package org.spring.rabbit.receiver.routing;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {
	@Bean
	public DirectExchange direct() {
		return new DirectExchange("tut.direct");
	}

	@Bean
	public Queue autoDeleteQueue3() {
		return new AnonymousQueue();
	}

	@Bean
	public Queue autoDeleteQueue4() {
		return new AnonymousQueue();
	}

	@Bean
	public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue3) {
		return BindingBuilder.bind(autoDeleteQueue3).to(direct).with("orange");
	}

	@Bean
	public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue3) {
		return BindingBuilder.bind(autoDeleteQueue3).to(direct).with("black");
	}

	@Bean
	public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue4) {
		return BindingBuilder.bind(autoDeleteQueue4).to(direct).with("green");
	}

	@Bean
	public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue4) {
		return BindingBuilder.bind(autoDeleteQueue4).to(direct).with("black");
	}
	
	@Bean
	public RoutingReceiver routingReceiver() {
		return new RoutingReceiver();
	}

}
