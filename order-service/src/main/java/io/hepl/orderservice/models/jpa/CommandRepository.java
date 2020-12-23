//Auteurs : HENDRICK Samuel et DELAVAL Kevin                                                
//Groupe : 2302                                                
//Projet : R.T.I.                                 
//Date de la création : 20/11/2020

package io.hepl.orderservice.models.jpa;

import io.hepl.orderservice.models.Command;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandRepository extends CrudRepository<Command, Integer> {
    List<Command> findByClient(String user);
    Command findByCommande(int id);
}


