//Auteur : HENDRICK Samuel                                                                                              
//Projet : stock-service                               
//Date de la création : 19/11/2020

package io.hepl.stockservice.resources;

import io.hepl.stockservice.models.Item;
import io.hepl.stockservice.models.ItemListResponse;
import io.hepl.stockservice.models.ItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping("/items")
public class ItemsResource {

    @Autowired
    private ArrayList<Item> items;

    @RequestMapping("")
    public ItemListResponse getAllItems()
    {
        return new ItemListResponse(items);
    }

    @RequestMapping("/{item-id}")
    public ItemResponse getItem(@PathVariable("item-id") String parameter)
    {
        Item item = getItemById(parameter);
        if(item != null)
        {
            return new ItemResponse(item);
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }

    private Item getItemById(String id)
    {
        for(Item item : items)
        {
            if(item.getId().equals(id))
            {
                return item;
            }
        }
        return null;
    }



}
