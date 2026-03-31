package com.currencyconverter.currency_converter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController //This tells Spring this class handles API requests
public class HelloController {

    @GetMapping("/hello") //When user goes to /hello
    public String sayHello(){
        return "Hello, Currency Converter is running!";
    }

    @GetMapping("/convert")
    public Map<String, Object> convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount){

        double conversionRate = 80; //dummy rate (USD to INR)
        double convertedAmount = amount * conversionRate;

        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("amount", amount);
        response.put("convertedAmount", convertedAmount);

        return response;
    }
}
