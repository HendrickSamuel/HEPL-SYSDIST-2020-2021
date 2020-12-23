//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.generalservice.Models.Order;

import io.hepl.generalservice.Models.General.ExposedItem;

import java.util.ArrayList;

public class Personne {

    private String userName;
    private int userId;
    private ArrayList<ExposedItem> items;

    public Personne() {
    }

    public Personne(String name) {
        this.userName = name;
    }

    public ArrayList<ExposedItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<ExposedItem> items) {
        this.items = items;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
