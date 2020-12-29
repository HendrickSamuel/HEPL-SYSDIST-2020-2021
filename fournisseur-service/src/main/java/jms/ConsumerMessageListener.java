package jms;

import javax.jms.*;
import java.util.Date;

public class ConsumerMessageListener implements MessageListener {
    private String consumerName;
    private Fournisseur fournisseur;

    public ConsumerMessageListener(String consumerName, Fournisseur fournisseur) {
        this.consumerName = consumerName;
        this.fournisseur = fournisseur;
    }
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(consumerName + " - " + String.format("%1$tY-%1$tm-%1$td,%1$tH:%1$tM:%1$tS", new Date()) + "\n\tReception du TextMessage : " + textMessage.getText());
                this.fournisseur.SendMsg(textMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else if(message instanceof StreamMessage){

        }else if(message instanceof ObjectMessage){

        }
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}
