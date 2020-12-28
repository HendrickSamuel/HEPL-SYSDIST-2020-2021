//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 28/12/2020

package io.hepl.generalservice.Security;

public class AuthentificationRequest {

    private String username;
    private String password;

    public AuthentificationRequest() {
    }

    public AuthentificationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
