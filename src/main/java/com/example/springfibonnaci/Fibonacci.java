package com.example.springfibonnaci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Configuration
public class Fibonacci {
    private static final String BAD_REQUEST_MESSAGE = "Please, enter n >= 0!\n";

    @Autowired
    public Service service;

    @GetMapping("/fibbonaci/{number}")
    @Cacheable(value = "number-cache", key = "'Number:' + #number")
    public String fibonacci(@PathVariable("number") int number){
        if (number < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE);
        }

        return service.countNthFib(number);
    }

    @CacheEvict(value = "number-cache", allEntries = true)
    @Scheduled(fixedDelay = 600000)
    public void cacheEvict() {

    }
}