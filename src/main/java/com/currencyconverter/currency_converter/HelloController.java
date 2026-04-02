package com.currencyconverter.currency_converter;

import com.currencyconverter.currency_converter.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // Mark this class as API controller
public class HelloController {

    private final CurrencyService currencyService;

    // Constructor (Sprint uses this to inject service)
    public HelloController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/convert")
    public Map<String, Object> convertCurrency(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount) {

        return currencyService.convert(from, to, amount);
    }
}
