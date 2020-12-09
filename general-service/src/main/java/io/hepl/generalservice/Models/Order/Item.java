//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.generalservice.Models.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class Item {

    private int itemId;

    private String id;
    private int quantity;
    private float unitPrice;
    private float tvaPrice;

    public Item() {
    }

    public Item(String id, int quantity, float unitPrice) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
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

    public float getTotalTVAPrice()
    {
        return getTvaPrice()*getQuantity();
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTvaPrice(float price) {
        this.tvaPrice = price;
    }

    public float getTvaPrice() {
        return tvaPrice;
    }

    public void setWanted(int wanted) {
        this.quantity = wanted;
    }

    @JsonIgnore
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setPrice(float price) {
        this.unitPrice = price;
    }

}
