//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 22/12/2020

package io.hepl.generalservice.Models.General;

import java.util.List;

public class ExposedClient {

    private ExposedCart _cart;
    private List<ExposedCommand> commands;
    private int _id;
    private String _nom;
    private float _porteFeuille;


    public ExposedClient() {
    }

    public ExposedCart getCart() {
        return _cart;
    }

    public void setCart(ExposedCart _cart) {
        this._cart = _cart;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getNom() {
        return _nom;
    }

    public void setNom(String _nom) {
        this._nom = _nom;
    }

    public float getPorteFeuille() {
        return _porteFeuille;
    }

    public void setPorteFeuille(float _porteFeuille) {
        this._porteFeuille = _porteFeuille;
    }

    public List<ExposedCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<ExposedCommand> commands) {
        this.commands = commands;
    }
}
