package com.example.springfibonnaci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@EnableCaching
@EnableScheduling
@Configuration
@SpringBootApplication
@RestController
public class SpringfibonnaciApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringfibonnaciApplication.class, args);
    }

    @GetMapping("/fibbonaci/{number}")
    @Cacheable(value = "number-cache", key = "'Number:' + #number")
    public String fibbonaci(@PathVariable("number") int number) throws InterruptedException {
        Thread.sleep(5000);
        if (number == 0) {
            return BigInteger.ZERO.toString();
        }

        BigInteger[][] a = {
                {BigInteger.ONE, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        };
        BigInteger[][] powerOfA = FibonnaciService.matrixPowerFast(a, number - 1);
        BigInteger nthFibonacci = powerOfA[0][0];

        return nthFibonacci.toString();
    }

    @CacheEvict(value = "number-cache", allEntries = true)
    @Scheduled(fixedDelay = 600000)
    public void cacheEvict() {

    }
}
