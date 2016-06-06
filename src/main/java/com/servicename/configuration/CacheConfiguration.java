package com.servicename.configuration;


import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfiguration {

    public static final String CACHE_STATIC_DATA = "CACHE_STATIC_DATA";

    public static final String CACHE_CUSTOMER_DATA = "CACHE_CUSTOMER_DATA";

    Logger logger = LoggerFactory.getLogger(CacheConfiguration.class);


    @Bean
    public Cache cacheStaticData() {
        return new GuavaCache(CACHE_STATIC_DATA, CacheBuilder.newBuilder()
                .expireAfterWrite(4, TimeUnit.HOURS)
                .build());
    }

    @Bean
    public Cache cacheCustomerData() {
        return new GuavaCache(CACHE_CUSTOMER_DATA, CacheBuilder.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .build());
    }


}
