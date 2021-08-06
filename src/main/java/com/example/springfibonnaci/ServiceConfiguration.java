package com.example.springfibonnaci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import javax.naming.ConfigurationException;

@RefreshScope
public class ServiceConfiguration {
    @Value("${fibonacci.mode: Default mode}")
    private String mode;

    @Bean
    Service fibonacciService() throws ConfigurationException {
        if ("prod".equals(mode)) {
            return new FibonacciProdService();
        } else if ("tech".equals(mode)) {
            return new FibonacciTechService();
        }

        throw new ConfigurationException("invalid value for 'fibonacci.mode':" + mode);
    }
}