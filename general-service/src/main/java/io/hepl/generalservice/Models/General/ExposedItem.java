//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 22/12/2020

package io.hepl.generalservice.Models.General;

public class ExposedItem {
    private String _id;
    private int _totalQuantity;
    private int _wantedQuantity;
    private float _tva;
    private float _price;
    private String _type;

    public ExposedItem() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int get_totalQuantity() {
        return _totalQuantity;
    }

    public void set_totalQuantity(int _totalQuantity) {
        this._totalQuantity = _totalQuantity;
    }

    public int get_wantedQuantity() {
        return _wantedQuantity;
    }

    public void set_wantedQuantity(int _wantedQuantity) {
        this._wantedQuantity = _wantedQuantity;
    }

    public float get_tva() {
        return _tva;
    }

    public void set_tva(float _tva) {
        this._tva = _tva;
    }

    public float get_price() {
        return _price;
    }

    public void set_price(float _price) {
        this._price = _price;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public float getTotalPrice()
    {
        return _price * _wantedQuantity;
    }

    public float getTotalTVAPrice()
    {
        return  (_price + ((_price * _tva)/100))*_wantedQuantity;
    }

}
