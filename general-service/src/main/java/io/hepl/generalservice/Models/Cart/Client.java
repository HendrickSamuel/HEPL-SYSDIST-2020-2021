//Auteur : HENDRICK Samuel                                                                                              
//Projet : cart-service                               
//Date de la création : 20/11/2020

package io.hepl.generalservice.Models.Cart;

import java.util.HashMap;

public class Client {
    private String userID;
    private HashMap<String, Integer> items;

    public Client() {
    }

    public Client(String userID) {
        this.userID = userID;
        items = new HashMap<>();
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }
}
