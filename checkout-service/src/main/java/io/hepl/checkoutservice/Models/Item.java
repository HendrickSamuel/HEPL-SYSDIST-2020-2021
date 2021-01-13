//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.checkoutservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Item {

    private int itemId;

    private String id;
    private int quantity;
    private float unitPrice;

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    @JsonProperty("wantedQuantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public float getTotalPrice()
    {
        return getUnitPrice()*getQuantity();
    }

    @JsonProperty("price")
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @JsonIgnore
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
