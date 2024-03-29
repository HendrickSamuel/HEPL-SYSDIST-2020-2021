//Auteurs : HENDRICK Samuel et DELAVAL Kevin                                                
//Groupe : 2302                                                
//Projet : R.T.I.                                 
//Date de la création : 25/11/2020

package io.hepl.checkoutservice.Models.jpa;


import io.hepl.checkoutservice.Models.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findById(int id);

    Client findByNom(String name);
}
