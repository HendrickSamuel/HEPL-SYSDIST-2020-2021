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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    @RequestMapping("/remove/{user}/{item}/{quantity}")
    public void removeItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        restTemplate.getForObject("http://cart-service/cart/remove/"+user+"/"+item+"/"+quantity, Object.class);
    }

    @RequestMapping("/get/{user}")
    public ExposedClient GetClient(@PathVariable String user)
    {
        Client client = restTemplate.getForObject("http://cart-service/cart/list/"+user, Client.class);
        //todo: verifier que le cart ne soit pas vide

        ExposedClient exposedClient = new ExposedClient();
        exposedClient.set_cart(new ExposedCart());

        ArrayList<ExposedItem> cart = exposedClient.get_cart().get_items();

        ItemListResponse itr = restTemplate.getForObject("http://stock-service/items/", ItemListResponse.class);
        for (Item item: itr.getItems() ) {
            if(client.getItems().containsKey(item.getId()))
            {
                ExposedItem exposedItem = new ExposedItem();
                exposedItem.set_id(item.getId());
                exposedItem.set_price(item.getPrice());
                exposedItem.set_totalQuantity(item.getStock());
                exposedItem.set_wantedQuantity(client.getItems().get(item.getId()));
                TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+item.getType(), TvaResponse.class);
                exposedItem.set_tva(tvaResponse.getTva());
                exposedItem.set_type(item.getType());
                cart.add(exposedItem);
            }
        }

        io.hepl.generalservice.Models.checkout.Client clientInfo = restTemplate.getForObject("http://checkout-service/users/name/"+client.getUserID(), io.hepl.generalservice.Models.checkout.Client.class);
        exposedClient.set_id(clientInfo.getId());
        exposedClient.set_nom(clientInfo.getNom());
        exposedClient.set_porteFeuille(clientInfo.getPorteFeuille());


        return exposedClient;
    }
}
