package com.currencyconverter.currency_converter;

import com.currencyconverter.currency_converter.model.Conversion;
import com.currencyconverter.currency_converter.service.CurrencyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
            @RequestParam double amount,
            @RequestParam(required = false) String apiKey) {

        // Simple API key check
        if (apiKey == null || !apiKey.equals("12345")) {
            throw new RuntimeException("Invalid or missing API Key");
        }

        return currencyService.convert(from, to, amount);
    }

//        @GetMapping("/history")
//        public List<Conversion> getHistory() {
//            return currencyService.getAllConversion();
//        }
    @GetMapping("/history")
    public Map<String, Object> getHistory(){

        List<Conversion> data = currencyService.getAllConversions();

        Map<String, Object> response = new HashMap<>();
        response.put("Success", true);
        response.put("data", data);

        return response;
    }
}