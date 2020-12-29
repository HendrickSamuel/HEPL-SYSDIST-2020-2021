package jms;

import model.Item;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Sender {
    //region Members
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;
    private MessageProducer producer;
    private String brokerAddress;
    private Message reponse;
    //endregion

    //region Constructor
    public Sender(String brokerAddress){
        this.brokerAddress = brokerAddress;
        this.Init();
    }

    //endregion
    //region Constructor
    private void Init(){
        try {
            connectionFactory = new ActiveMQConnectionFactory(this.brokerAddress);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("enset.topic");
            producer = session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void Start(){
        try {
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void Close(){
        try {
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void Send(Object obj) throws JMSException {
        if(obj instanceof Item){
           ObjectMessage objectMessage = this.session.createObjectMessage((Item)obj);
           this.producer.send(objectMessage);
        }else if(obj instanceof String){
            TextMessage textMessage = this.session.createTextMessage((String)obj);
            this.producer.send(textMessage);
        }
    }
    //endregion

    //region Methodes

    //endregion
}
