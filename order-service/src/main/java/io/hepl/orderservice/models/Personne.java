//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.orderservice.models;

import java.util.HashMap;

public class Personne {

    private String userID;
    private HashMap<String, Integer> items;

    public Personne() {
    }

    public Personne(String name) {
        this.userID = name;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
