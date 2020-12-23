//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 22/12/2020

package io.hepl.generalservice.Models.General;

import java.util.ArrayList;
import java.util.List;

public class ExposedCommand {
    private int commande;

    private List<ExposedItem> items;
    private String client;
    private String status; // PREPARATION / EXPEDIEE / RECEPTIONNEE
    private String mode;
    private int userId;

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }

    public List<ExposedItem> getItems() {
        return items;
    }

    public void setItems(List<ExposedItem> items) {
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
        for(ExposedItem item: items)
        {
            total += item.getTotalPrice();
        }
        return total;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
