package jms;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import java.net.URI;

public class ActiveMQBroker {
    public static void main(String[] args) {
        try {
            BrokerService broker = BrokerFactory.createBroker(new URI(
                    "broker:(tcp://localhost:61616)"));
            broker.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
