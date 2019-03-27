package org.spring.rabbit.receiver.topics;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TopicReceiverConfig {

		@Bean
	    public TopicExchange topic() {
	        return new TopicExchange("tut.topic");
	    }


	        @Bean
	        public Queue autoDeleteQueue5() {
	            return new AnonymousQueue();
	        }

	        @Bean
	        public Queue autoDeleteQueue6() {
	            return new AnonymousQueue();
	        }

	        @Bean
	        public Binding binding5a(TopicExchange topic, 
	            Queue autoDeleteQueue5) {
	            return BindingBuilder.bind(autoDeleteQueue5)
	                .to(topic)
	                .with("*.orange.*");
	        }

	        @Bean
	        public Binding binding5b(TopicExchange topic, 
	            Queue autoDeleteQueue5) {
	            return BindingBuilder.bind(autoDeleteQueue5)
	                .to(topic)
	                .with("*.*.rabbit");
	        }

	        @Bean
	        public Binding binding6a(TopicExchange topic, 
	            Queue autoDeleteQueue6) {
	            return BindingBuilder.bind(autoDeleteQueue6)
	                .to(topic)
	                .with("lazy.#");
	        }


	    @Bean
	    public TopicsReceiver topicsReceiver() {
	        return new TopicsReceiver();
	    }

	}
