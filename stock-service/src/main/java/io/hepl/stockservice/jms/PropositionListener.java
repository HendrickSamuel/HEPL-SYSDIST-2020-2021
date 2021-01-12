package io.hepl.stockservice.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.CountDownLatch;

public class PropositionListener implements MessageListener {

    @Override
    public void onMessage(Message message) {

    }
}
