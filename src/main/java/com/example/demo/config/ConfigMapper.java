package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ConfigMapper {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

