package com.currencyconverter.currency_converter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service //Service class
public class CurrencyService {

    public Map<String, Object> convert(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam double amount){

        RestTemplate restTemplate = new RestTemplate();

        // Relaced API Key from ExchangeRate-API - https://v6.exchangerate-api.com/v6/ca891384bb40e6a172a97792/latest/USD
        String apiKey = "ca891384bb40e6a172a97792";

        // USD as base for simplicity first
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

        // Call external API and get response as Map
        Map responseFromApi = restTemplate.getForObject(url, Map.class);

        // Extract rates
        Map<String, Double> rates = (Map<String, Double>) responseFromApi.get("conversion_rates");

        // Get conversion rate for target currency
        double conversionRate = rates.get(to);

        double convertedAmount = amount * conversionRate;

        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("amount", amount);
        response.put("convertedAmount", convertedAmount);

        return response;
    }
}
