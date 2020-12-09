//Auteur : HENDRICK Samuel                                                                                              
//Projet : checkout-service                               
//Date de la création : 25/11/2020

package io.hepl.generalservice.Models.checkout;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Payement {

    private int id;

    private Client client;

    private int commande;


    public Payement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getCommande() {
        return commande;
    }

    public void setCommande(int commande) {
        this.commande = commande;
    }
}
