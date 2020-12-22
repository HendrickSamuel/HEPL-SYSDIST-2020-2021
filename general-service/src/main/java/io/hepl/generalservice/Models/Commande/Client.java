//Auteur : HENDRICK Samuel                                                                                              
//Projet : cart-service                               
//Date de la création : 20/11/2020

package io.hepl.generalservice.Models.Commande;

import java.util.ArrayList;

public class Client {
    private String userID;
    private ArrayList<Item> items;

    public Client() {
    }

    public Client(String userID) {
        this.userID = userID;
        items = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
