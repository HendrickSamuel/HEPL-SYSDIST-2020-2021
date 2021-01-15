package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Receiver {
    //region Members
    public ConnectionFactory connectionFactory;
    public Connection connection;
    public Session session;
    public Destination destination;
    public MessageConsumer consumer;
    public String brokerAddress;
    public Message reponse;
    //endregion

    //region Constructor
    public Receiver(String brokerAddress){
        this.brokerAddress = brokerAddress;
        this.Init();
    }
    //endregion

    //region Methodes
    public void Init(){
        try {
            connectionFactory =  new ActiveMQConnectionFactory(this.brokerAddress);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("fournisseur.topic");
            consumer = session.createConsumer(destination, "type='demande-stock'");
            //this.HandleMessage();
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
    private void HandleMessage()  {
        try {
            consumer.setMessageListener(message -> {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("Consumer - Reception du TextMessage : " + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }else if(message instanceof StreamMessage){

                }else if(message instanceof ObjectMessage){

                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    //endregion

    //region Get/Set
    public Message getReponse() {
        return reponse;
    }

    public void setReponse(Message reponse) {
        this.reponse = reponse;
    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public MessageConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }

    public String getBrokerAddress() {
        return brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    //endregion
}
