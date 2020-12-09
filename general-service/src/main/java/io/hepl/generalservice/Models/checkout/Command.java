//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.generalservice.Models.checkout;

import java.util.ArrayList;
import java.util.List;


public class Command {

    private int commande;

    private List<Item> items;
    private String client;
    private String status; // PREPARATION / EXPEDIEE / RECEPTIONNEE

    public Command(String client) {
        this.client = client;
        items = new ArrayList<>();
    }

    public Command() {

    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getAmount() {
        float total = 0;
        for(Item item: items)
        {
            total += item.getTotalPrice();
        }
        return total;
    }
}
