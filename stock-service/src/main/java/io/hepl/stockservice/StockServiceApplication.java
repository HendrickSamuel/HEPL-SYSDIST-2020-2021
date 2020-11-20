package io.hepl.stockservice;

import io.hepl.stockservice.models.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class StockServiceApplication {

    @Bean
    public ArrayList<Item> getStockItems()
    {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item("item-1","Danettes","bon yaourt", "bouffe", 4.65f , 10));
        items.add(new Item("item-2","BonBons","petites description sympa", "autre", 2.35f , 5));
        items.add(new Item("item-3","Clavier corsair K-70","clavier mécanique", "electronique", 145f , 2));
        items.add(new Item("item-4","Lot de bics","pack de 5 bics 4 couleurs", "autre", 2.10f , 35));
        items.add(new Item("item-5","Guitar électrique","elle est stylée", "autre", 399.99f , 1));

        return items;
    }

    public static void main(String[] args) {
        SpringApplication.run(StockServiceApplication.class, args);
    }

}
