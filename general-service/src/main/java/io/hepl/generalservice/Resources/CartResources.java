//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 06/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Cart.Client;
import io.hepl.generalservice.Models.Order.Command;
import io.hepl.generalservice.Models.Order.Personne;
import io.hepl.generalservice.Models.Stock.Item;
import io.hepl.generalservice.Models.Stock.StockAvailabilityResponse;
import io.hepl.generalservice.Models.TVA.TvaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    public Client GetClient(@PathVariable String user)
    {
        Client client = restTemplate.getForObject("http://cart-service/cart/list/"+user, Client.class);
        return client;
    }

    @RequestMapping("/checkout/{user}")
    public io.hepl.generalservice.Models.checkout.Client checkout(@PathVariable String user)
    {
        Client client = GetClient(user);
        Set<String> keys = client.getItems().keySet();
        io.hepl.generalservice.Models.Commande.Client clientToCheckout = new io.hepl.generalservice.Models.Commande.Client(client.getUserID());

        ArrayList<Item> items = new ArrayList<>();

        for (String key : keys)
        {
            StockAvailabilityResponse stock = restTemplate.getForObject("http://stock-service/stock/"+key+"/"+client.getItems().get(key), StockAvailabilityResponse.class);
            if(stock.isAvailable())
            {
                stock.getItem().setWanted(client.getItems().get(key));
                TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+stock.getItem().getType(), TvaResponse.class);
                stock.getItem().setTva(tvaResponse.getTva());
                items.add(stock.getItem());
            }
            else
            {
                // generer un warning pour la disponibilité
            }
        }

        Personne personne = new Personne(user);
        personne.setItems(items);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Personne> httpEntity = new HttpEntity<>(personne, headers);

        Command commande = restTemplate.postForObject("http://order-service/", httpEntity, Command.class);

        HttpEntity<Command> httpCommande = new HttpEntity<>(commande, headers);
        io.hepl.generalservice.Models.checkout.Client clientretour =
                restTemplate.postForObject("http://checkout-service/checkout/", httpCommande, io.hepl.generalservice.Models.checkout.Client.class );

        return clientretour;



        //checkout la commande si le montant est suffisant
    }

}
