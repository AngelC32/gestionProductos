package com.products.backend_products;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:variables.properties")
public class AppConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
