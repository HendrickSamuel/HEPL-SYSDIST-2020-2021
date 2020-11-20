package io.hepl.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CartServiceApplication {

    @Bean
    public ArrayList<String> getListe()
    {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

}
