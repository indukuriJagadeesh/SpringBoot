package com.springboot.producer;

import com.springboot.model.Email;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.Enumeration;

@RestController
@RequestMapping("/jms/publish")
public class Producer {

    @Autowired
    public JmsTemplate jmsTemplate;

    /*@Value("{$jms.test.queue}")
    private String destination;

    @Value("{$jms.mailbox.queue}")
    private String destination1;*/

    @Autowired
    Queue queue;



    public Queue produceMailQueue(){
        return new ActiveMQQueue("mailbox.queue");
    }


    @RequestMapping("/{message}")
    public String testPublishMessage(@PathVariable("message") final String message){
        jmsTemplate.convertAndSend(queue,message);
        System.out.println("Message published ..."+message);
        return "Message Published Successfully..!";
    }

    @RequestMapping("/test/{emailBody}")
    public String testPublishJmsMessage(@PathVariable("emailBody") final String emailBody){
        Email email = new Email("info@example.com", emailBody);

        jmsTemplate.convertAndSend(produceMailQueue(),email);
        System.out.println("Email published ..."+email);
        return "Email Msg Published Successfully..!";
    }

}
