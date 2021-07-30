package com.example.springfibonnaci;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;

@Profile("prod")
@RestController
public class Fibonacci {

    @GetMapping("/fibbonaci/{number}")
    @Cacheable(value = "number-cache", key = "'Number:' + #number")
    public String fibbonaci(@PathVariable("number") int number) {
        if (number < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please, enter n >= 0!\n");
        }

        BigInteger res = FibonacciService.countNthFib(number);

        return res.toString();
    }

    @CacheEvict(value = "number-cache", allEntries = true)
    @Scheduled(fixedDelay = 600000)
    public void cacheEvict() {

    }
}