//Auteur : HENDRICK Samuel                                                                                              
//Projet : checkout-service                               
//Date de la création : 24/11/2020

package io.hepl.checkoutservice.Resources;


import io.hepl.checkoutservice.Models.Client;
import io.hepl.checkoutservice.Models.Command;
import io.hepl.checkoutservice.Models.Payement;
import io.hepl.checkoutservice.Models.ResponseMessage;
import io.hepl.checkoutservice.Models.jpa.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutResource {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping(path = "")
    public ResponseMessage request(@RequestBody Command command)
    {
        Client client = clientRepository.findById(command.getUserId()); //todo: si pas de client alors 404 ?

        Payement payement = new Payement(client, command.getCommande(),
                command.getAmount(),
                (command.getMode().equalsIgnoreCase("Normal")) ? 5 : 10);

        if(client.getPorteFeuille() -  payement.getTotal() >= 0)
        {
            client.getPayements().add(payement);
            //todo: annuler si pas assez dans le portefeuille
            client.setPorteFeuille(client.getPorteFeuille()-command.getAmount());
            client = clientRepository.save(client);

            return new ResponseMessage(true, payement.getTotal()+" has been removed from your acount", "OK");
        }
        else
        {
            return new ResponseMessage(false, "you don't have enough", "ERROR");
        }

    }

}
