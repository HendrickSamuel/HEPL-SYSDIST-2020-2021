//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 22/12/2020

package io.hepl.generalservice.Models.General;

public class ExposedClient {

    private ExposedCart _cart;
    private int _id;
    private String _nom;
    private float _porteFeuille;

    public ExposedClient() {
    }

    public ExposedCart get_cart() {
        return _cart;
    }

    public void set_cart(ExposedCart _cart) {
        this._cart = _cart;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nom() {
        return _nom;
    }

    public void set_nom(String _nom) {
        this._nom = _nom;
    }

    public float get_porteFeuille() {
        return _porteFeuille;
    }

    public void set_porteFeuille(float _porteFeuille) {
        this._porteFeuille = _porteFeuille;
    }
}
