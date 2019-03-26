package org.rabbit.receiver.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ReceiveLogsDirect2 {

	  private static final String EXCHANGE_NAME = "direct_logs";

	  public static void main(String[] argv) throws Exception {
	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.exchangeDeclare(EXCHANGE_NAME, "direct");
	    String queueName = channel.queueDeclare().getQueue();

		/*
		 * if (argv.length < 1) {
		 * System.err.println("Usage: ReceiveLogsDirect [info] [warning] [error]");
		 * System.exit(1); }
		 */

	   // for (String severity : argv) {
	    //这个for循环表示一个一个queue可以绑定多个routingkey
	        channel.queueBind(queueName, EXCHANGE_NAME, "routingkey1");
	   // }
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

	    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
	        String message = new String(delivery.getBody(), "UTF-8");
	        System.out.println(" [x] Received '" +
	            delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
	    };
	    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
	  }
	}
