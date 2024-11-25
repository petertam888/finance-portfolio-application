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
            String url = "https://www.google.com/search?q=NASDAQ:" + stockCode;

            Document document = Jsoup.connect(url).get();

            String companyName;

//            Element companyNameDiv = document.select("div.D(ib) h1.Fz\\(18px\\)").first();
            // in html file, the http content is: <h1 class="D(ib) Fz(18px)">{Apple Inc. (AAPL)}</h1>
            Element companyNameDiv = document.select("#knowledge-finance-wholepage__entity-summary > div.aviV4d > g-card-section > div > g-card-section > div.OiIFo.knowledge-finance-wholepage__collapse-area-expand > div > div > span").first();
            if (companyNameDiv == null){
                companyName = stockCode;
            }
            else {

                // Get the text content from the stock price elementg
                companyName = companyNameDiv.text().trim();
            }


            // Extract the stock price from the fin-streamer element
            Element stockPriceElement = document.select("#knowledge-finance-wholepage__entity-summary > div.aviV4d > g-card-section > div > g-card-section > div.wGt0Bc > div.PZPZlf > span:nth-child(1) > span > span.IsqQVc.NprOob.wT3VGc").first();

            if (stockPriceElement != null) {
                // Get the text content from the stock price element
                String stock_price = stockPriceElement.text().trim();
                String modified_stock_price = stock_price.replace(",", "");
                Float stockPrice = Float.valueOf(modified_stock_price);

                FetchedStockInfoDTO stockInfo = new FetchedStockInfoDTO(companyName, stockCode, stockPrice);

                return stockInfo;


            } else {
                System.out.println("Unable to find the stock price element. Check the HTML structure.");
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }






}
