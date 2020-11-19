package io.hepl.tvaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

@SpringBootApplication
public class TvaServiceApplication {

    @Bean
    public HashMap<String, Float> getTvaHashMap()
    {
        HashMap<String, Float> hashMap = new HashMap<>();
        hashMap.put("livres", 6f);
        hashMap.put("jeux", 18f);
        hashMap.put("bouffe", 3f);
        hashMap.put("essentiel", 0f);
        hashMap.put("bricolage", 21f);
        hashMap.put("autres", 21f);
        return hashMap;
    }

    public static void main(String[] args) {
        SpringApplication.run(TvaServiceApplication.class, args);
    }
}
