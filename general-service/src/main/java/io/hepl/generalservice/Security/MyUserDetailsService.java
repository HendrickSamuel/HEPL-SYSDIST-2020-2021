//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 28/12/2020

package io.hepl.generalservice.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private ArrayList<User> users;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        for (User user: users) {
            if(user.getUsername().equalsIgnoreCase(s))
                return user;
        }
        return null;
    }

    @Bean
    public ArrayList<User> getAllAuthorizedUsers()
    {
       return new ArrayList<User>(
               Arrays.asList(
                       new User("Asp-net","shop", new ArrayList<>()),
                       new User("samuel","samuel", new ArrayList<>()),
                       new User("benedict","benedict", new ArrayList<>())
               ));
    }
}
