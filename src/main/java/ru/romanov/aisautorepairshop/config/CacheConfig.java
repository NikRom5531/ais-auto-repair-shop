package ru.romanov.aisautorepairshop.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

@Configuration
@EnableCaching
@EnableAspectJAutoProxy
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("employees", "items", "operations", "orders", "warehouses",
                "inventory_requirements", "item_quantities");
    }
}
