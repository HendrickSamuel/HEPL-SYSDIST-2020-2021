//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 28/12/2020

package io.hepl.generalservice.Security;

public class AuthentificationResponse {

    private final String jwt;

    public AuthentificationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
