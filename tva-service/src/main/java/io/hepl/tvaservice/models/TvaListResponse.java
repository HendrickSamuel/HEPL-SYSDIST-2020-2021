//Auteur : HENDRICK Samuel                                                                                              
//Projet : tva-service                               
//Date de la création : 19/11/2020

package io.hepl.tvaservice.models;

import java.util.HashMap;
import java.util.Properties;

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
