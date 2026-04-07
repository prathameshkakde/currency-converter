package com.currencyconverter.currency_converter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.currencyconverter.currency_converter.model.Conversion;
import com.currencyconverter.currency_converter.repository.ConversionRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service //Service class
public class CurrencyService {

    private final ConversionRepository conversionRepository;

    public CurrencyService(ConversionRepository conversionRepository) {
        this.conversionRepository = conversionRepository;
    }

    public Map<String, Object> convert(
            String from,
            String to,
            double amount){

        if (amount <= 0){
            throw new RuntimeException("Amount must be greater than zero");
        }

        if (from == null || from.isEmpty() || to == null || to.isEmpty()){
            throw new RuntimeException("Currency values must not be empty");
        }

        RestTemplate restTemplate = new RestTemplate();

        // Relaced API Key from ExchangeRate-API - https://v6.exchangerate-api.com/v6/ca891384bb40e6a172a97792/latest/USD
        String apiKey = "ca891384bb40e6a172a97792";

        // USD as base for simplicity first
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

        // Call external API and get response as Map
        Map responseFromApi = restTemplate.getForObject(url, Map.class);

        // Extract rates
        Map<String, Double> rates = (Map<String, Double>) responseFromApi.get("conversion_rates");

        // Get value safely
        Double rate = rates.get(to);

        if(rate == null){
            throw new RuntimeException("Invalid target currency: " + to);
        }
        // Get conversion rate for target currency
        double conversionRate = rate;

        double convertedAmount = amount * conversionRate;

        Map<String, Object> data = new HashMap<>();
        data.put("from", from);
        data.put("to", to);
        data.put("amount", amount);
        data.put("convertedAmount", convertedAmount);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("data", data);

        // Create entity object
        Conversion conversion = new Conversion(from, to, amount, convertedAmount);

        // Save to database
        conversionRepository.save(conversion);

        return response;
    }

    public List<Conversion> getAllConversions(){
        return conversionRepository.findAll();
    }
}
