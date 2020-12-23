//Auteur : HENDRICK Samuel                                                                                              
//Projet : checkout-service                               
//Date de la création : 23/12/2020

package io.hepl.checkoutservice.Models;

public class ResponseMessage {

    private boolean success;
    private String message;
    private String state; //OK - WARNING - ERROR

    public ResponseMessage() {
    }

    public ResponseMessage(boolean success, String message, String state) {
        this.success = success;
        this.message = message;
        this.state = state;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
