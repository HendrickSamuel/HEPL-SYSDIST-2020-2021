//Auteur : HENDRICK Samuel                                                                                              
//Projet : order-service                               
//Date de la création : 20/11/2020

package io.hepl.orderservice.resources;

import io.hepl.orderservice.models.Command;
import io.hepl.orderservice.models.Item;
import io.hepl.orderservice.models.Personne;
import io.hepl.orderservice.models.jpa.CommandRepository;
import io.hepl.orderservice.models.jpa.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/")
public class OrderResource {

    @Autowired
    private CommandRepository repo;

    @Autowired
    private ItemRepository repository;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Command request(@RequestBody Personne person)
    {
        Command command = new Command(person.getUserID());
        command.setStatus("EN TRAITEMENT");
        //repo.save(command);
        if(person.getItems() != null)
        {
            Set<String> keys = person.getItems().keySet();
            for(String key : keys)
            {
                Item item = new Item();
                item.setQuantity(person.getItems().get(key));
                item.setId(key);
                item.setUnitPrice(23f); //todo: appel au microservice du stock ?
                item.setCommande(command);
                //repository.save(item);
                command.getItems().add(item);
            }
        }
        command = repo.save(command);

        return command;
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
}
