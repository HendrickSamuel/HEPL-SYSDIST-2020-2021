//Auteur : HENDRICK Samuel                                                                                              
//Projet : general-service                               
//Date de la création : 28/12/2020

package io.hepl.generalservice.Resources;

import com.netflix.discovery.converters.Auto;
import io.hepl.generalservice.Security.AuthentificationRequest;
import io.hepl.generalservice.Security.AuthentificationResponse;
import io.hepl.generalservice.Security.JwtUtil;
import io.hepl.generalservice.Security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthResource {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody AuthentificationRequest authentificationRequest,
                                             HttpServletResponse response) throws  Exception{

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authentificationRequest.getUsername());
        if(userDetails != null && userDetails.getPassword().equals(authentificationRequest.getPassword()))
        {
            final String jwt = jwtUtil.generateToken(userDetails);
            response.addHeader("token", jwt);
            return ResponseEntity.ok(new AuthentificationResponse(jwt));
        }
        else
        {
            throw new Exception("Incorrect username and password");
        }

    }
}
