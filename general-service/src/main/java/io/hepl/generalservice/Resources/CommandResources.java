//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 12/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Cart.Client;
import io.hepl.generalservice.Models.General.ExposedClient;
import io.hepl.generalservice.Models.General.ExposedItem;
import io.hepl.generalservice.Models.General.ResponseMessage;
import io.hepl.generalservice.Models.Order.Command;
import io.hepl.generalservice.Models.Order.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/command")
public class CommandResources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CartResources cart;

    @RequestMapping("/preview/{user}")
    public Command checkout(@PathVariable String user)
    {
        ExposedClient exposedClient = restTemplate.getForObject("http://general-service/cart/get/"+user, ExposedClient.class);
        for (ExposedItem item : exposedClient.getCart().getItems()) {
            restTemplate.getForObject("http://stock-service/remove/"+item.getId()+"/"+item.getWantedQuantity(), Object.class);
        }

        Personne personne = new Personne(user);
        personne.setItems(exposedClient.getCart().getItems());
        personne.setUserId(exposedClient.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Personne> httpEntity = new HttpEntity<>(personne, headers);

        Command commande = restTemplate.postForObject("http://order-service/", httpEntity, Command.class);
        restTemplate.getForObject("http://cart-service/cart/remove/"+personne.getUserName(), Object.class);
        return commande;
    }

    @RequestMapping("/checkout/{id}")
    public ResponseMessage CheckoutCommand(@PathVariable int id, @RequestParam(required = false) String methode)
    {
        methode = (methode == null) ? "Normal" : methode;


        Command commande = restTemplate.getForObject("http://order-service/"+id, Command.class);
        if(!commande.getStatus().equalsIgnoreCase("EN ATTENTE DE VALIDATION"))
        {
            return new ResponseMessage(false, "Command already checked-out", "ERROR");
        }
        restTemplate.getForObject("http://order-service/update/"+id+"/CHECKOUT", Object.class);

        commande.setMode(methode);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Command> httpEntity = new HttpEntity<>(commande, headers);

        ResponseMessage res = restTemplate.postForObject("http://checkout-service/checkout", httpEntity, ResponseMessage.class);
        if(res.isSuccess())
        {
            return res;
        }
        else
        {
            restTemplate.getForObject("http://order-service/update/"+id+"/ANULEE", Object.class);
            return res;
        }
    }
}
