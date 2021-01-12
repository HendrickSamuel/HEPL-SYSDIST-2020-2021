package io.hepl.stockservice.jms;
import io.hepl.stockservice.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
public class Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
    LinkedList<Item> itemsFournisseurs;
    @JmsListener(destination = "${fournisseur.topic}", containerFactory = "jmsContainerFactory", selector = "type = 'proposition-stock'")
    public void receiveProposition(Item item){
        logger.info("Message Recu : " + item);
        itemsFournisseurs.add(item);
    }

    public LinkedList<Item> AttentePropositionItem(){
        int attente = 5;
        LinkedList<Item> items = new LinkedList<>();
        try {
            for (int i = 1; i <= attente; i++) {
                logger.warn("AttentePropositionItem - attente de 5s, tentative n"+i);
                wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
        for (int i = 0; i < itemsFournisseurs.size(); i++) {
            items.add(itemsFournisseurs.get(i));
        }
        itemsFournisseurs.clear();
        return items;
    }
}
