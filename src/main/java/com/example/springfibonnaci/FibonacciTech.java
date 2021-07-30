package com.example.springfibonnaci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@Profile("tech")
@RefreshScope
@RestController
public class FibonacciTech {
    @Value("${text.copyright: Default Copyright}")
    private String copyright;

    @GetMapping("/fibbonaci/{number}")
    public String fibbonaciTech(@PathVariable("number") int number) {
        return this.copyright;
    }
}
