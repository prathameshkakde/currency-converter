package com.currencyconverter.currency_converter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //This tells Spring this class handles API requests
public class HelloController {

    @GetMapping("/hello") //When user goes to /hello
    public String sayHello(){
        return "Hello, Currency Converter is running!";
    }
}
