//Auteur : HENDRICK Samuel                                                                                              
//Projet : cart-service                               
//Date de la création : 19/11/2020

package io.hepl.cartservice.resources;

import io.hepl.cartservice.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/cart")
public class CartResources {

    @Autowired
    private ArrayList<Client> clients;

    @RequestMapping("/add/{user}/{item}/{quantity}")
    public void addItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        Client target = null;
        for(Client client : clients)
        {
            if(client.getUserID().equals(user))
            target = client;
        }
        if(target == null)
        {
            target = new Client(user);
            clients.add(target);
        }

        if(target.getItems().containsKey(item))
        {
            int newQuantity = target.getItems().get(item) + quantity;
            target.getItems().put(item,newQuantity);
        }
        else
        {
            target.getItems().put(item, quantity);
        }

    }

    @RequestMapping("/remove/{user}/{item}/{quantity}")
    public void removeItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        Client target = null;
        for(Client client : clients)
        {
            if(client.getUserID().equals(user))
                target = client;
        }
        if(target == null)
        {
            target = new Client(user);
            clients.add(target);
        }

        if(target.getItems().containsKey(item))
        {
            int newQuantity = target.getItems().get(item) - quantity;
            if(newQuantity <= 0)
            target.getItems().remove(item);
            else
            target.getItems().put(item, newQuantity);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }

    @RequestMapping("/remove/{user}")
    public void removeCart(@PathVariable String user)
    {
        for(Client client : clients)
        {
            if(client.getUserID().equals(user))
                clients.remove(client);
            break;
        }
    }

    @RequestMapping("/swap/{oldUser}/{newUser}")
    public void swapClient(@PathVariable String oldUser, @PathVariable String newUser)
    {
        Client oldClient = null;
        Client newClient = null;
        for(Client client : clients)
        {
            if(client.getUserID().equals(oldUser))
               oldClient = client;
            else if(client.getUserID().equals(newUser))
                newClient = client;
        }

        if(newClient != null)
        {
            HashMap<String, Integer> mapTmp = new HashMap<>();
            mapTmp.putAll(newClient.getItems());
            mapTmp.putAll(oldClient.getItems()); //on sait qu'il y a une exception mais ce n'est pas dans un systeme distrib
            newClient.setItems(mapTmp);
        }
        else
        {
            oldClient.setUserID(newUser);
        }
    }

    @RequestMapping("/list/{user}")
    public Client getUsersCart(@PathVariable String user)
    {
        for(Client client : clients)
        {
            if(client.getUserID().equals(user))
                return client;
        }

        return new Client(user);
    }
}
