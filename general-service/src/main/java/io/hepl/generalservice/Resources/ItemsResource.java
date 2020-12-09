//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 06/12/2020

package io.hepl.generalservice.Resources;

import io.hepl.generalservice.Models.Stock.ItemListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/items")
public class ItemsResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/")
    public ItemListResponse getAllItems()
    {
        ItemListResponse itr = restTemplate.getForObject("http://stock-service/items/", ItemListResponse.class);
        return itr;
    }

}
