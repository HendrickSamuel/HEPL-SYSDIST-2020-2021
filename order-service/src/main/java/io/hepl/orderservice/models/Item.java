//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.orderservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Item {

    @ManyToOne
    private Command commande;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    private String id;
    private int quantity;
    private float unitPrice;
    private float tvaPrice;

    public Item() {
    }

    public Item(Command commande, String id, int quantity, float unitPrice) {
        this.commande = commande;
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    @JsonIgnore
    public Command getCommande() {
        return commande;
    }

    public void setCommande(Command commande) {
        this.commande = commande;
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
