package com.servicename.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import falcon.easypay.dao.CustomerDataService;
import falcon.easypay.dao.CustomerDataServiceImpl;
import falcon.easypay.model.DbConnectionPoolBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DbConnectionPoolBean.class)
public class DatabaseConfiguration {

    @Bean
    @Autowired(required = false)
    public HikariConfig hikariConfig (DbConnectionPoolBean connectionPoolBean){
        if(connectionPoolBean!=null && connectionPoolBean.isValid()){
            HikariConfig  hikariConfig = new HikariConfig();
            hikariConfig.setUsername(connectionPoolBean.getUser());
            hikariConfig.setPassword(connectionPoolBean.getPassword());
            hikariConfig.setDriverClassName(connectionPoolBean.getDriverClassName());
            hikariConfig.setJdbcUrl(connectionPoolBean.getUrl());
            hikariConfig.setMaximumPoolSize(connectionPoolBean.getMaxPoolSize());
            return hikariConfig;
        }
        throw new IllegalStateException("Database configuration not loaded.");
    }


    @Bean(destroyMethod = "close")
    @Autowired
    public DataSource dataSource(HikariConfig hikariConfig){
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @Autowired
    public CustomerDataService customerDataService(JdbcTemplate jdbcTemplate){
        return new CustomerDataServiceImpl(jdbcTemplate);
    }


}
