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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/checkout")
public class CheckoutResource {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(path = "/",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Client request(@RequestBody Command command)
    {
        Client client = new Client("Samuel", "pwd", "adresse", 3650.2f);
        Payement payement = new Payement(client, command.getCommande());
        client.getPayements().add(payement);
        client.setPorteFeuille(client.getPorteFeuille()-command.getAmount());
        client = clientRepository.save(client);

        return client;

    }


}
