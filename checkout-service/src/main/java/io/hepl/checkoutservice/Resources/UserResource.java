//Auteur : HENDRICK Samuel                                                                                              
//Projet : checkout-service                               
//Date de la création : 22/12/2020

package io.hepl.checkoutservice.Resources;

import io.hepl.checkoutservice.Models.Client;
import io.hepl.checkoutservice.Models.Command;
import io.hepl.checkoutservice.Models.jpa.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping(path = "/id/{userId}")
    public Client requestone(@PathVariable int userId)
    {
        return clientRepository.findById(userId);
    }

    @RequestMapping(path = "/name/{userId}")
    public Client requesttwo(@PathVariable String userId)
    {
        return clientRepository.findByNom(userId);
    }
}
