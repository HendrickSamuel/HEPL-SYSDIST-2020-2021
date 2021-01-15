package io.hepl.stockservice.jms;
import io.hepl.stockservice.models.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class Receiver {

    private HashMap<String, ArrayList<String>> pendingRequests;
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    public Receiver(HashMap<String, ArrayList<String>> pendingRequests) {
        this.pendingRequests = pendingRequests;
    }

    @JmsListener(destination = "${fournisseur.topic}", selector = "type='proposition-stock'")
    public void receiveProposition(String item){
        logger.info("Message Recu : " + item);
        String id = item.split("##")[0];
        if(pendingRequests.containsKey(id))
            pendingRequests.get(id).add(item);
    }

    public synchronized LinkedList<Item> AttentePropositionItem(String id){
        if(pendingRequests.containsKey(id))
            return null;

        pendingRequests.put(id, new ArrayList<>());

        int attente = 5;

        try {
            for (int i = 1; i <= attente; i++) {
                logger.warn("AttentePropositionItem - attente de 5s, tentative n"+i);
                wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }

        LinkedList<Item> items = new LinkedList<>();
        for (String string : pendingRequests.get(id))
        {
            String idNewItem = string.split("##")[0];
            String quantity = string.split("##")[1];
            String price = string.split("##")[2];
            items.add(new Item(idNewItem, Float.parseFloat(price), Integer.parseInt(quantity)));
        }

        pendingRequests.remove(id);

        return items;

    }
}
