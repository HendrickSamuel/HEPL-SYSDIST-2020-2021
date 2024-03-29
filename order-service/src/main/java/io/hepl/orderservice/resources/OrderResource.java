//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.orderservice.resources;

import io.hepl.orderservice.models.Command;
import io.hepl.orderservice.models.CommandList;
import io.hepl.orderservice.models.Item;
import io.hepl.orderservice.models.Personne;
import io.hepl.orderservice.models.jpa.CommandRepository;
import io.hepl.orderservice.models.jpa.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/")
public class OrderResource {

    @Autowired
    private CommandRepository repo;

    @Autowired
    private ItemRepository repository;

    @PostMapping(path = "",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Command request(@RequestBody Personne person)
    {
        Command command = new Command(person.getUserName());
        command.setStatus("EN ATTENTE DE VALIDATION");
        command.setUserId(person.getUserId());
        if(person.getItems() != null)
        {
            ArrayList<Item> items = person.getItems();
            command.setItems(items);
        }

        //todo: supprimer les autres en attente
        command = repo.save(command);

        return command;
    }

    @RequestMapping("update/{commande}/{state}")
    public void updateState(@PathVariable int commande, @PathVariable String state)
    {
        Command commandToReturn = repo.findByCommande(commande);
        commandToReturn.setStatus(state);
        repo.save(commandToReturn);
    }

    @RequestMapping("updateMode/{commande}/{state}")
    public void updateMode(@PathVariable int commande, @PathVariable String mode)
    {
        Command commandToReturn = repo.findByCommande(commande);
        commandToReturn.setMode(mode);
        repo.save(commandToReturn);
    }

    @RequestMapping("{commande}")
    public Command getCommande(@PathVariable int commande)
    {
        Command commandToReturn = repo.findByCommande(commande);
        if(commandToReturn != null)
        return commandToReturn;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    @RequestMapping("/remove/{commande}")
    public void removeCommande(@PathVariable int commande)
    {
        repo.deleteById(commande);
    }

    @RequestMapping("/client/{client}")
    public CommandList getUserCommands(@PathVariable String client)
    {
        return new CommandList(repo.findByClient(client));
    }

    @RequestMapping("/current/{client}")
    public Command getWaitingCommande(@PathVariable String client){
        Command commandToReturn = repo.findCommandByClientAndStatus(client, "EN ATTENTE DE VALIDATION");
        if(commandToReturn != null)
            return commandToReturn;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }
}
