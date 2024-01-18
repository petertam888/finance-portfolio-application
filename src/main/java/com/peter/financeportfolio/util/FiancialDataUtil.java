package com.peter.financeportfolio.util;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


public class FiancialDataUtil {
    private final String apiKey = "your_api_key";

    public String getLatestStockPrice(String symbol){
        String apiUrl = "https://api.example.com/stock/{symbol}/quote?apikey=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiUrl, String.class, symbol);
    }
}
