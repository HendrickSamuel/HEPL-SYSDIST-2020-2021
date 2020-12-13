//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 12/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Cart.Client;
import io.hepl.generalservice.Models.Order.Command;
import io.hepl.generalservice.Models.Order.Personne;
import io.hepl.generalservice.Models.checkout.Item;
import io.hepl.generalservice.Models.Stock.StockAvailabilityResponse;
import io.hepl.generalservice.Models.TVA.TvaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Set;

@RestController
@RequestMapping("/command")
public class CommandResources {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CartResources cart;

    @RequestMapping("/validate/{user}")
    public Command checkout(@PathVariable String user)
    {
        Client client = cart.GetClient(user);
        Set<String> keys = client.getItems().keySet();
        io.hepl.generalservice.Models.Commande.Client clientToCheckout = new io.hepl.generalservice.Models.Commande.Client(client.getUserID());

        ArrayList<Item> items = new ArrayList<>();

        for (String key : keys)
        {
            StockAvailabilityResponse stock = restTemplate.getForObject("http://stock-service/stock/"+key+"/"+client.getItems().get(key), StockAvailabilityResponse.class);
            if(stock.isAvailable())
            {
                io.hepl.generalservice.Models.checkout.Item newItem = new io.hepl.generalservice.Models.checkout.Item();
                newItem.setWanted(client.getItems().get(key));
                newItem.setDescription(stock.getItem().getDescription());
                newItem.setId(stock.getItem().getId());
                newItem.setPrice(stock.getItem().getPrice());

                TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+stock.getItem().getType(), TvaResponse.class);
                newItem.setTva(tvaResponse.getTva());
                items.add(newItem);
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
        return commande;
    }

    @RequestMapping("/checkout/{id}")
    public Command CheckoutCommand(@PathVariable int id)
    {
        Command commande = restTemplate.getForObject("http://order-service/"+id, Command.class);
        return commande;
        //todo: vider le stock

        //todo: envoyer sur le service checkout + retour si oui ou non tu as assez d'argent
    }

    //todo: annuler une commande ??


    //todo: ajouter une méthode pour avoir acces au portefeuil

}
