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

    public ArrayList<ExposedItem> get_items() {
        return _items;
    }

    public void set_items(ArrayList<ExposedItem> _items) {
        this._items = _items;
    }

    public float get_total()
    {
        float price = 0;
        for (ExposedItem exposedItem: get_items()) {
            price += exposedItem.getTotalPrice();
        }
        return price;
    }

    public float get_totalTVA()
    {
        float price = 0;
        for (ExposedItem exposedItem: get_items()) {
            price += exposedItem.getTotalTVAPrice();
        }
        return price;
    }
}
