//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 23/12/2020

package io.hepl.orderservice.models;

import java.util.List;

public class CommandList {
    private List<Command> commands;

    public CommandList() {
    }

    public CommandList(List<Command> commands) {
        this.commands = commands;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
