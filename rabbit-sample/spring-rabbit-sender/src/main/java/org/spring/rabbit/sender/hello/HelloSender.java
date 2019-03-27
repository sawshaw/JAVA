package org.spring.rabbit.sender.hello;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
public class HelloSender {
	public static int i=0;

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queue;
    
    //为方便测试运行其他发送定时任务时最好去掉EnableScheduling这个注解
    //@Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void send() {
    	System.out.println(++i);
        String message = "Hello World!"+i;
        try {
        	this.template.convertAndSend(queue.getName(), message);
        }catch(Exception e) {
        	System.out.println("连接失败");
        	this.template.convertAndSend(queue.getName(), message);
        }
        System.out.println(" [x] Sent '" + message + "'");
    }
}