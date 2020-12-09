//Auteur : HENDRICK Samuel                                                                                              
//Projet : stock-service                               
//Date de la création : 19/11/2020

package io.hepl.generalservice.Models.Stock;

public class ItemResponse {

    private Item item;

    public ItemResponse() {
    }

    public ItemResponse(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
