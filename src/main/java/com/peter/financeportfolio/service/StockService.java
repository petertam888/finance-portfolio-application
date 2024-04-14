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
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Map<String, Object> fetchStockInformation(String stockCode){
        Map<String, Object> stockInfo = new HashMap<>();
        try {
            String url = "http://finance.yahoo.com/quote/" + stockCode;

            Document document = Jsoup.connect(url).get();


//            Element companyNameDiv = document.select("div.D(ib) h1.Fz\\(18px\\)").first();
            // in html file, the http content is: <h1 class="D(ib) Fz(18px)">{Apple Inc. (AAPL)}</h1>
            Element companyNameDiv = document.select("h1.D\\(ib\\).Fz\\(18px\\)").first();

            if (companyNameDiv != null) {

                // Get the text content from the stock price elementg
                String companyName = companyNameDiv.text().trim();

                stockInfo.put("companyName", companyName);

            }
            else {
                System.out.println("Unable to find the company name div. Check the HTML structure.");

            }

            Element codeAndStockPriceDiv = document.select("div.D\\(ib\\).Mend\\(20px\\)").first();

            if (codeAndStockPriceDiv != null) {
                // Extract the stock price from the fin-streamer element
                Element stockPriceElement = codeAndStockPriceDiv.select("fin-streamer[data-field=regularMarketPrice]").first();

                if (stockPriceElement != null) {
                    // Get the text content from the stock price element
                    String stock_price = stockPriceElement.text().trim();
                    Float stockPrice = Float.valueOf(stock_price);

                    stockInfo.put("stockCode", stockCode);
                    stockInfo.put("stockPrice", stockPrice);


                } else {
                    System.out.println("Unable to find the stock price element. Check the HTML structure.");
                }
            } else {
                System.out.println("Unable to find the target div. Check the HTML structure.");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockInfo;
    }

    public List<Stock> getAllStocks(){
        return stockRepository.getAllStocks();
    }

//    public Map<String, String> buyStock(String stockSymbol, Integer numberOfStock){
//        return stockRepository.getAllStocks();
//    }




}
