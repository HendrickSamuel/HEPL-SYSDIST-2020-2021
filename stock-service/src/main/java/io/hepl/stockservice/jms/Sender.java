package io.hepl.stockservice.jms;

import io.hepl.stockservice.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.*;

public class Sender {
    @Autowired
    JmsTemplate jmsTemplate;

//    @Autowired
//    Topic topic;

    public String publish(String msg){
        jmsTemplate.convertAndSend(msg);
        return "Info Envoyee !";
    }

    public void DemandeStock(Item item) {
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage();
                objectMessage.setObject(item);
                objectMessage.setStringProperty("type", "demande-stock");
                return objectMessage;
            }
        });
    }
}
