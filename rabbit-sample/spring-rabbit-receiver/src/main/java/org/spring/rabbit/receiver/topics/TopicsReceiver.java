package org.spring.rabbit.receiver.topics;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

public class TopicsReceiver {
	@RabbitListener(queues = "#{autoDeleteQueue5.name}")
    public void receive1(String in) throws InterruptedException {
        receive(in, 5);
    }

    @RabbitListener(queues = "#{autoDeleteQueue6.name}")
    public void receive2(String in) throws InterruptedException {
        receive(in, 6);
    }

    public void receive(String in, int receiver) throws 
        InterruptedException {
        StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + receiver + " [x] Received '" 
            + in + "'");
        doWork(in);
        watch.stop();
        System.out.println("instance " + receiver + " [x] Done in " 
            + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
}