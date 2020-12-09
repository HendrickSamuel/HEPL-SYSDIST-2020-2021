//Auteur : HENDRICK Samuel                                                                                              
//Projet : checkout-service                               
//Date de la création : 24/11/2020

package io.hepl.checkoutservice.Resources;


import io.hepl.checkoutservice.Models.Client;
import io.hepl.checkoutservice.Models.Command;
import io.hepl.checkoutservice.Models.Payement;
import io.hepl.checkoutservice.Models.jpa.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutResource {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(path = "/")
    public Client request(@RequestBody Command command)
    {
        Client client = new Client("Samuel", "pwd", "adresse", 3650.2f);
        Payement payement = new Payement(client, command.getCommande());
        client.getPayements().add(payement);
        client.setPorteFeuille(client.getPorteFeuille()-command.getAmount());
        client = clientRepository.save(client);

        return client;

    }

    @PostMapping(path = "/test")
    public Client res(@RequestBody Command command)
    {
        Client client = clientRepository.findById(1);
        Payement payement = new Payement(client, command.getCommande());
        client.getPayements().add(payement);
        client.setPorteFeuille(client.getPorteFeuille()-command.getAmount());
        client = clientRepository.save(client);

        return client;

    }
}
