//Auteurs : HENDRICK Samuel et DELAVAL Kevin                                                
//Groupe : 2302                                                
//Projet : R.T.I.                                 
//Date de la création : 25/11/2020

package io.hepl.checkoutservice.Models.jpa;


import io.hepl.checkoutservice.Models.Payement;
import org.springframework.data.repository.CrudRepository;

public interface PayementRepository extends CrudRepository<Payement, Long> {
}
