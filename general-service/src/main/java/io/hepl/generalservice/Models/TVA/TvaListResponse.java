//Auteur : HENDRICK Samuel                                                                                              
//Projet : tva-service                               
//Date de la création : 19/11/2020

package io.hepl.generalservice.Models.TVA;

import java.util.HashMap;

public class TvaListResponse {

    HashMap<String, Float> values;

    public TvaListResponse() {
    }

    public TvaListResponse(HashMap<String, Float> values) {
        this.values = values;
    }

    public HashMap<String, Float> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Float> values) {
        this.values = values;
    }
}
