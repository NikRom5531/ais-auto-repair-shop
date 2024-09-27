package ru.romanov.aisautorepairshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AisAutoRepairShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(AisAutoRepairShopApplication.class, args);
    }
}
