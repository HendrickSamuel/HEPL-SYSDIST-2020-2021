package io.hepl.stockservice.jms;

import com.netflix.discovery.converters.Auto;
import io.hepl.stockservice.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Sender {
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Topic queue;

//    @Autowired
//    Topic topic;

    public String publish(String msg){
        jmsTemplate.convertAndSend(msg);
        return "Info Envoyee !";
    }

    public void DemandeStock(Item item) {
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(item.getId());
                textMessage.setStringProperty("type", "demande-stock");
                return textMessage;
            }
        });
    }
}
