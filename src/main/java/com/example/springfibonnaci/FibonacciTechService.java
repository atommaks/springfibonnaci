package com.example.springfibonnaci;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
public class FibonacciTechService implements Service {
    @Value("${text.copyright: Default Copyright}")
    private static String copyright;

    public String countNthFib(int n) {
        return copyright;
    }
}
