package com.springboot.listener;

import com.google.gson.Gson;
import com.springboot.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.AbstractAdaptableMessageListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class Listener {

	@Autowired
	private MessageConverter messageConverter;

	@JmsListener(destination = "helloworld.test.queue")
	public void receiveMessage(final Message jsonMessage) throws JMSException {
        //((AbstractAdaptableMessageListener.MessagingMessageConverterAdapter.LazyResolutionMessage) jsonMessage).message
		System.out.println("Received JMS Message: " + jsonMessage);

        if (jsonMessage instanceof TextMessage) {
            String str = ((TextMessage) jsonMessage).getText();
            //payload = s.getBytes(UTF_8);
            System.out.print("\t>>message value is >> "+str);
        }

	}

	//@JmsListener(destination = "mailbox.queue", containerFactory = "myFactory")
    @JmsListener(destination = "mailbox.queue")
	public void receiveMailMessage(final Email message) throws JMSException{
		System.out.println("Received message email "+message);
		//System.out.println("Received <" + email + ">");
	}
	/*@JmsListener(destination = "helloworld.test.queue")
	@SendTo("outbound.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		if(jsonMessage instanceof TextMessage) {
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			Map map = new Gson().fromJson(messageData, Map.class);
			response  = "Hello " + map.get("name");
		}
		return response;
	}*/

}