//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 06/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Cart.Client;
import io.hepl.generalservice.Models.General.ExposedCart;
import io.hepl.generalservice.Models.General.ExposedClient;
import io.hepl.generalservice.Models.General.ExposedItem;
import io.hepl.generalservice.Models.Stock.Item;
import io.hepl.generalservice.Models.Stock.ItemListResponse;
import io.hepl.generalservice.Models.TVA.TvaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Set;

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

    @RequestMapping("/swap/{oldUser}/{newUser}")
    public void swapCart(@PathVariable String oldUser, @PathVariable String newUser)
    {
        try {
            restTemplate.getForObject("http://cart-service/cart/swap/"+oldUser+"/"+newUser, Object.class);
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
    public ExposedClient GetClient(@PathVariable String user)
    {
        Client client;
        try
        {
            client = restTemplate.getForObject("http://cart-service/cart/list/"+user, Client.class);
        }
        catch (HttpClientErrorException.NotFound ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }

        ExposedClient exposedClient = new ExposedClient();
        exposedClient.setCart(new ExposedCart());

        ArrayList<ExposedItem> cart = exposedClient.getCart().getItems();

        ItemListResponse itr = restTemplate.getForObject("http://stock-service/items/", ItemListResponse.class);
        for (Item item: itr.getItems()) {
            if(client.getItems().containsKey(item.getId()))
            {
                ExposedItem exposedItem = new ExposedItem();
                exposedItem.setName(item.getName());
                exposedItem.setId(item.getId());
                exposedItem.setPrice(item.getPrice());
                exposedItem.setTotalQuantity(item.getStock());
                exposedItem.setWantedQuantity(client.getItems().get(item.getId()));
                TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+item.getType(), TvaResponse.class);
                exposedItem.setTva(tvaResponse.getTva());
                exposedItem.setType(item.getType());
                cart.add(exposedItem);
            }
        }

        io.hepl.generalservice.Models.checkout.Client clientInfo = restTemplate.getForObject("http://checkout-service/users/name/"+client.getUserID(), io.hepl.generalservice.Models.checkout.Client.class);
        if(clientInfo != null)
        {
            exposedClient.setId(clientInfo.getId());
            exposedClient.setNom(clientInfo.getNom());
            exposedClient.setPorteFeuille(clientInfo.getPorteFeuille());
        }
        else
        {
            exposedClient.setId(-1);
            exposedClient.setNom("visiteur");
            exposedClient.setPorteFeuille(-1);
        }

        return exposedClient;
    }
}
