//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 22/12/2020

package io.hepl.generalservice.Models.General;

import java.util.ArrayList;

public class ExposedCart {

    private ArrayList<ExposedItem> _items;

    public ExposedCart() {
        _items = new ArrayList<>();
    }

    public ArrayList<ExposedItem> getItems() {
        return _items;
    }

    public void setItems(ArrayList<ExposedItem> _items) {
        this._items = _items;
    }

    public float getTotal()
    {
        float price = 0;
        for (ExposedItem exposedItem: getItems()) {
            price += exposedItem.getTotalPrice();
        }
        return price;
    }

    public float getTotalTVA()
    {
        float price = 0;
        for (ExposedItem exposedItem: getItems()) {
            price += exposedItem.getTotalTVAPrice();
        }
        return price;
    }
}
