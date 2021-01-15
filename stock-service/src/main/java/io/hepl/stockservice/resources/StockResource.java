//Auteur : HENDRICK Samuel                                                                                              
//Projet : stock-service                               
//Date de la création : 19/11/2020

package io.hepl.stockservice.resources;

import com.netflix.discovery.converters.Auto;
import io.hepl.stockservice.jms.Receiver;
import io.hepl.stockservice.jms.Sender;
import io.hepl.stockservice.models.Item;
import io.hepl.stockservice.models.StockAvailabilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Predicate;

@RestController
@RequestMapping("/stock")
public class StockResource {

    @Autowired
    private Sender sender;

    @Autowired Receiver receiver;

    @Autowired
    private ArrayList<Item> items;

    @RequestMapping("/{itemId}/{quantity}")
    public StockAvailabilityResponse getAvailability(@PathVariable String itemId, @PathVariable int quantity)
    {
        Item item = getItemById(itemId);
        if(item != null)
        {
            return new StockAvailabilityResponse(item, (item.getStock() >= quantity));
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    private Item getItemById(String id)
    {
        for(Item item : items)
        {
            if(item.getId().equals(id))
            {
                return item;
            }
        }
        return null;
    }

    @RequestMapping("remove/{itemId}/{quantity}")
    public void RemoveItem(@PathVariable String itemId, @PathVariable int quantity)
    {
        Item item = getItemById(itemId);
        if(item != null)
        {
            item.setStock(item.getStock() - quantity);
            if(item.getStock() <= 0)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DemandeRechargerStock(item);
                        ComparaisonPropositionItem(item.getId());
                    }
                }).start();

            }
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @RequestMapping("/launchTest")
    public String test()
    {
        Item item = getItemById("item-4");
        new Thread(new Runnable() {
            @Override
            public void run() {
                DemandeRechargerStock(item);
                ComparaisonPropositionItem(item.getId());
            }
        }).start();
        return "ok";
    }

    private void DemandeRechargerStock(Item item) {
        sender.DemandeStock(item);
    }

    private void ComparaisonPropositionItem(String id) {
        LinkedList<Item> items = receiver.AttentePropositionItem(id);
        if(items!= null && items.size() >= 1)
        {
            Collections.sort(items, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return Float.compare(o1.getPrice(), o2.getPrice());
                }
            });
            AddItem(items.getFirst().getId(), items.getFirst().getStock());
        }
    }


    @RequestMapping("add/{itemId}/{quantity}")
    public void AddItem(@PathVariable String itemId, @PathVariable int quantity)
    {
        Item item = getItemById(itemId);
        if(item != null)
        {
            item.setStock(item.getStock() + quantity);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }
}
