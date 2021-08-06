package com.example.springfibonnaci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@org.springframework.stereotype.Service
@ConditionalOnProperty(
        value="text.mode",
        havingValue = "tech"
)
public class FibonacciTechService implements com.example.springfibonnaci.Service {
    @Value("${text.copyright: Default Copyright}")
    private String copyright;

    public String countNthFib(int n) {
        return copyright;
    }
}
