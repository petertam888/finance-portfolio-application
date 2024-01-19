package com.peter.financeportfolio.service;
import com.peter.financeportfolio.model.Stock;
import com.peter.financeportfolio.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Map<String, String> fetchStockInformation(String stockSymbol){
        Map<String, String> stockInfo = new HashMap<>();
        Stock stock = new Stock();
        try {
            String url = "http://finance.yahoo.com/quote/" + stockSymbol;

            Document document = Jsoup.connect(url).get();


//            Element companyNameDiv = document.select("div.D(ib) h1.Fz\\(18px\\)").first();
            // in html file, the http content is: <h1 class="D(ib) Fz(18px)">{Apple Inc. (AAPL)}</h1>
            Element companyNameDiv = document.select("h1.D\\(ib\\).Fz\\(18px\\)").first();

            if (companyNameDiv != null) {

                // Get the text content from the stock price elementg
                String companyName = companyNameDiv.text().trim();

                stockInfo.put("companyName", companyName);
                stock.setCompanyName(companyName);

            }
            else {
                System.out.println("Unable to find the company name div. Check the HTML structure.");

            }

            Element symbolAndStockPriceDiv = document.select("div.D\\(ib\\).Mend\\(20px\\)").first();

            if (symbolAndStockPriceDiv != null) {
                // Extract the stock price from the fin-streamer element
                Element stockPriceElement = symbolAndStockPriceDiv.select("fin-streamer[data-field=regularMarketPrice]").first();

                if (stockPriceElement != null) {
                    // Get the text content from the stock price element
                    String stockPrice = stockPriceElement.text().trim();

                    stockInfo.put("stockSymbol", stockSymbol);
                    stockInfo.put("stockPrice", stockPrice);
                    stock.setStockSymbol(stockSymbol);
                    stock.setStockPrice(stockPrice);

                } else {
                    System.out.println("Unable to find the stock price element. Check the HTML structure.");
                }
            } else {
                System.out.println("Unable to find the target div. Check the HTML structure.");

            }
            stockRepository.save(stock);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockInfo;
    }


}
