package io.hepl.stockservice.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
public class Config {

//    @Value("${activemq.broker-url}")
//    private String brokerUrl;

//    @Value("${fournisseur.topic}")
//    private String fournisseurTopicName;

//    @Value("${test.topic}")
//    private String testTopicName;

//    @Bean
//    public Topic topicFourniseur(){
//        return new ActiveMQTopic(testTopicName);
//    }

//    @Bean
//    public ActiveMQConnectionFactory activeMQConnectionFactory(){
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
//        factory.setBrokerURL(brokerUrl);
//        return factory;
//    }

    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsContainerFactory(SingleConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
      //  factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setConnectionFactory(connectionFactory);
        configurer.configure(factory, connectionFactory);
        return factory;
    }
//    @Bean
//    public JmsTemplate jmsTemplate(){
//        return new JmsTemplate(this.activeMQConnectionFactory());
//    }
}
