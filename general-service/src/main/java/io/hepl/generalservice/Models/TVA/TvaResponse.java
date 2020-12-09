//Auteur : HENDRICK Samuel                                                                                              
//Projet : tva-service                               
//Date de la création : 19/11/2020

package io.hepl.generalservice.Models.TVA;

public class TvaResponse {

    private String category;
    private float tva;

    public TvaResponse() {
    }

    public TvaResponse(String category, float tva) {
        this.category = category;
        this.tva = tva;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getTva() {
        return tva;
    }

    public void setTva(float tva) {
        this.tva = tva;
    }
}
