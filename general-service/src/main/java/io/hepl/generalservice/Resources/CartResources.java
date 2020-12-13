//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 06/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Cart.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/cart")
public class CartResources {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add/{user}/{item}/{quantity}")
    public void addItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        try {
            restTemplate.getForObject("http://cart-service/cart/add/"+user+"/"+item+"/"+quantity, Object.class);
        } catch (HttpClientErrorException ex) {
            System.out.println(ex.getStatusCode());
        }
    }

    @RequestMapping("/remove/{user}/{item}/{quantity}")
    public void removeItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        restTemplate.getForObject("http://cart-service/cart/remove/"+user+"/"+item+"/"+quantity, Object.class);
    }

    @RequestMapping("/get/{user}")
    public Client GetClient(@PathVariable String user)
    {
        Client client = restTemplate.getForObject("http://cart-service/cart/list/"+user, Client.class);
        return client;
    }


}
