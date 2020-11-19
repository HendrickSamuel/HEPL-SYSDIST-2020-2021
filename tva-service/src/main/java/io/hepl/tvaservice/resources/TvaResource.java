//Auteur : HENDRICK Samuel                                                                                              
//Projet : tva-service                               
//Date de la création : 19/11/2020

package io.hepl.tvaservice.resources;

import io.hepl.tvaservice.models.TvaListResponse;
import io.hepl.tvaservice.models.TvaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/")
public class TvaResource {

    @Autowired
    private HashMap<String, Float> hashMap;

    @RequestMapping("")
    public TvaListResponse getAllTva()
    {
        return new TvaListResponse(hashMap);
    }

    @RequestMapping("/{type}")
    public TvaResponse getTvaFromProduct(@PathVariable String type)
    {
        return new TvaResponse(type,hashMap.getOrDefault(type, hashMap.get("autres")));
    }

}
