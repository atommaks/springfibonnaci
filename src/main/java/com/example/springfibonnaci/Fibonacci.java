package com.example.springfibonnaci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;


@RestController
@RefreshScope
public class Fibonacci {
    private static final String BAD_REQUEST_MESSAGE = "Please, enter n >= 0!\n";

    @Value("${text.copyright: Default Copyright}")
    private String copyright;

    @Autowired
    private Environment environment;

    @GetMapping("/fibbonaci/{number}")
    @Cacheable(value = "number-cache", key = "'Number:' + #number")
    public String fibonacci(@PathVariable("number") int number) {
        if (isTechProfileActive()) {
            return copyright;
        }

        if (number < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQUEST_MESSAGE);
        }

        BigInteger res = FibonacciService.countNthFib(number);

        return res.toString();
    }

    @CacheEvict(value = "number-cache", allEntries = true)
    @Scheduled(fixedDelay = 600000)
    public void cacheEvict() {

    }

    private boolean isTechProfileActive() {
        for (final String profileName : environment.getActiveProfiles()) {
            if("tech".equals(profileName)){
                return true;
            }
        }
        return false;
    }
}