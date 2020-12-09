//Auteur : HENDRICK Samuel                                                                                              
//Projet : checkout-service                               
//Date de la création : 25/11/2020

package io.hepl.generalservice.Models.checkout;

import java.util.ArrayList;
import java.util.List;


public class Client {

    private int id;

    private String nom;
    private String password;
    private String adresse;

    private float porteFeuille;

    private List<Payement> payements;

    public Client(String nom, String password, String adresse, float porteFeuille) {
        this.nom = nom;
        this.password = password;
        this.adresse = adresse;
        this.porteFeuille = porteFeuille;

        payements = new ArrayList<>();

    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public float getPorteFeuille() {
        return porteFeuille;
    }

    public void setPorteFeuille(float porteFeuille) {
        this.porteFeuille = porteFeuille;
    }

    public List<Payement> getPayements() {
        return payements;
    }

    public void setPayements(List<Payement> payements) {
        this.payements = payements;
    }
}
