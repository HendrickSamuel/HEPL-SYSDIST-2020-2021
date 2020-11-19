//Auteur : HENDRICK Samuel                                                                                              
//Projet : stock-service                               
//Date de la création : 19/11/2020

package io.hepl.stockservice.model;

public class StockAvailabilityResponse {

    private Item item;
    private boolean available;

    public StockAvailabilityResponse() {
    }

    public StockAvailabilityResponse(Item item, boolean available) {
        this.item = item;
        this.available = available;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
