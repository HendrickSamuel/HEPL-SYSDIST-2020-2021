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

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public int getTotalQuantity() {
        return _totalQuantity;
    }

    public void setTotalQuantity(int _totalQuantity) {
        this._totalQuantity = _totalQuantity;
    }

    public int getWantedQuantity() {
        return _wantedQuantity;
    }

    public void setWantedQuantity(int _wantedQuantity) {
        this._wantedQuantity = _wantedQuantity;
    }

    public float getTva() {
        return _tva;
    }

    public void setTva(float _tva) {
        this._tva = _tva;
    }

    public float getPrice() {
        return _price;
    }

    public void setPrice(float _price) {
        this._price = _price;
    }

    public String getType() {
        return _type;
    }

    public void setType(String _type) {
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
