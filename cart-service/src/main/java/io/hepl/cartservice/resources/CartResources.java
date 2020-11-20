//Auteur : HENDRICK Samuel                                                                                              
//Projet : cart-service                               
//Date de la création : 19/11/2020

package io.hepl.cartservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/cart")
public class CartResources {

    @Autowired
    private ArrayList<String> strings;

    @RequestMapping("/add/{user}/{item}/{quantity}")
    public void addItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        strings.add(item);
    }

    @RequestMapping("/remove/{user}/{item}/{quantity}")
    public void removeItemToCart(@PathVariable String user, @PathVariable String item, @PathVariable int quantity)
    {
        strings.remove(item);
    }

    @RequestMapping("/list/{user}")
    public ArrayList<String> removeItemToCart(@PathVariable String user)
    {
        return strings;
    }
}
