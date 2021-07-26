package com.example.springfibonnaci;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@Import({CacheConfiguration.class, SсheduleConfugiration.class})
public class Fibonacci {
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
    //@Scheduled(fixedDelay = 600000)
    @Scheduled(fixedDelay = 10000)
    public void cacheEvict() {

    }
}