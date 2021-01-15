//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 12/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Cart.Client;
import io.hepl.generalservice.Models.General.ExposedClient;
import io.hepl.generalservice.Models.General.ExposedCommand;
import io.hepl.generalservice.Models.General.ExposedItem;
import io.hepl.generalservice.Models.General.ResponseMessage;
import io.hepl.generalservice.Models.Order.Command;
import io.hepl.generalservice.Models.Order.Personne;
import io.hepl.generalservice.Models.Stock.Item;
import io.hepl.generalservice.Models.Stock.ItemResponse;
import io.hepl.generalservice.Models.TVA.TvaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

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

        ExposedClient exposedClient = cart.GetClient(user);
        if(exposedClient.getCart() == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }

        for (ExposedItem item : exposedClient.getCart().getItems()) {
            restTemplate.getForObject("http://stock-service/stock/remove/"+item.getId()+"/"+item.getWantedQuantity(), Object.class);
        }

        Personne personne = new Personne(user.toLowerCase());
        personne.setItems(exposedClient.getCart().getItems());

        personne.setUserId(exposedClient.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Personne> httpEntity = new HttpEntity<>(personne, headers);

        Command commande = restTemplate.postForObject("http://order-service/", httpEntity, Command.class);
        restTemplate.getForObject("http://cart-service/cart/remove/"+personne.getUserName(), Object.class);
        for(ExposedItem item : commande.getItems())
        {
            ItemResponse stockitem = restTemplate.getForObject("http://stock-service/items/"+item.getId(), ItemResponse.class);
            item.setType(stockitem.getItem().getType());
            item.setName(stockitem.getItem().getName());
            TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+item.getType(), TvaResponse.class);
            item.setTva(tvaResponse.getTva());
        }

        return commande;
    }

    @RequestMapping("/checkout/{id}")
    public ResponseMessage CheckoutCommand(@PathVariable int id, @RequestParam(required = false, defaultValue = "Normal") String methode)
    {
        Command commande = restTemplate.getForObject("http://order-service/"+id, Command.class);
        if(!commande.getStatus().equalsIgnoreCase("EN ATTENTE DE VALIDATION"))
        {
            return new ResponseMessage(false, "Command already checked-out", "ERROR");
        }

        restTemplate.getForObject("http://order-service/update/"+id+"/CHECKOUT", Object.class);
        //restTemplate.getForObject("http://order-service/updateMode/"+id+"/"+methode, Object.class);

        commande.setMode(methode);
        for(ExposedItem item : commande.getItems())
        {
            ItemResponse stockitem = restTemplate.getForObject("http://stock-service/items/"+item.getId(), ItemResponse.class);
            item.setType(stockitem.getItem().getType());
            item.setName(stockitem.getItem().getName());
            TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+item.getType(), TvaResponse.class);
            item.setTva(tvaResponse.getTva());
        }

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
            restTemplate.getForObject("http://order-service/update/"+id+"/ANNULEE", Object.class);
            return res;
        }
    }

    @RequestMapping("list/{user}")
    public ExposedClient getUsersCommands(@PathVariable String user)
    {
        ExposedClient client = restTemplate.getForObject("http://order-service/client/"+user, ExposedClient.class);
        for (ExposedCommand commande : client.getCommands())
        {
            for(ExposedItem item : commande.getItems())
            {
                ItemResponse stockitem = restTemplate.getForObject("http://stock-service/items/"+item.getId(), ItemResponse.class);
                item.setType(stockitem.getItem().getType());
                item.setName(stockitem.getItem().getName());
                TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+item.getType(), TvaResponse.class);
                item.setTva(tvaResponse.getTva());
            }
        }
        return client;
    }

    @RequestMapping("/current/{user}")
    public Command getCurrentCommand(@PathVariable String user)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

       // HttpEntity<Personne> httpEntity = new HttpEntity<>(personne, headers);
        try{
            Command commande = restTemplate.getForObject("http://order-service/current/" + user, Command.class);
            for(ExposedItem item : commande.getItems())
            {
                ItemResponse stockitem = restTemplate.getForObject("http://stock-service/items/"+item.getId(), ItemResponse.class);
                item.setType(stockitem.getItem().getType());
                item.setName(stockitem.getItem().getName());
                TvaResponse tvaResponse = restTemplate.getForObject("http://tva-service/"+item.getType(), TvaResponse.class);
                item.setTva(tvaResponse.getTva());
            }
            return commande;
        }catch (HttpClientErrorException.NotFound e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
        }
    }
}
