package com.peter.financeportfolio.service;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.peter.financeportfolio.model.Stock;
import com.peter.financeportfolio.repository.StockRepository;
import com.peter.financeportfolio.dto.FetchedStockInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public FetchedStockInfoDTO fetchStockInformation(String stockCode){
        try {
            String url = "http://finance.yahoo.com/quote/" + stockCode;

            Document document = Jsoup.connect(url).get();

//            Element companyNameDiv = document.select("div.D(ib) h1.Fz\\(18px\\)").first();
            // in html file, the http content is: <h1 class="D(ib) Fz(18px)">{Apple Inc. (AAPL)}</h1>
            Element companyNameDiv = document.select("#nimbus-app > section > section > section > article > section.container.svelte-okyrr7 > div.top.svelte-okyrr7 > div > section > h1").first();

            if (companyNameDiv != null) {

                // Get the text content from the stock price elementg
                String companyName = companyNameDiv.text().trim();


                // Extract the stock price from the fin-streamer element
                Element stockPriceElement = document.select("#nimbus-app > section > section > section > article > section.container.svelte-okyrr7 > div.bottom.svelte-okyrr7 > div.price.svelte-okyrr7 > section > div > section:nth-child(1) > div.container.svelte-mgkamr > fin-streamer.livePrice.svelte-mgkamr > span").first();

                if (stockPriceElement != null) {
                    // Get the text content from the stock price element
                    String stock_price = stockPriceElement.text().trim();
                    Float stockPrice = Float.valueOf(stock_price);

                    FetchedStockInfoDTO stockInfo = new FetchedStockInfoDTO(companyName, stockCode, stockPrice);

                    return stockInfo;


                } else {
                    System.out.println("Unable to find the stock price element. Check the HTML structure.");
                }

            }
            else {
                System.out.println("Unable to find the company name div. Check the HTML structure.");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }






}
