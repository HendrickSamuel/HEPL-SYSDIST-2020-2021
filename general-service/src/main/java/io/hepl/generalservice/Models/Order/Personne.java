//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.generalservice.Models.Order;

import io.hepl.generalservice.Models.Stock.Item;

import java.util.ArrayList;

public class Personne {

    private String userID;
    private ArrayList<Item> items;

    public Personne() {
    }

    public Personne(String name) {
        this.userID = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
