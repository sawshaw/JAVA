package org.spring.rabbit.sender.subscribe;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class SubSender {
	public static int i=0;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;
    //为方便测试运行其他发送定时任务时最好去掉EnableScheduling这个注解
    //@Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void send() {
    	System.out.println(++i);
        String message = "pub/sub!"+i;
        try {
        	this.template.convertAndSend(fanout.getName(),"", message);
        }catch(Exception e) {
        	System.out.println("连接失败");
        	this.template.convertAndSend(fanout.getName(),"", message);
        }
        System.out.println(" [x] Sent '" + message + "'");
    }
}