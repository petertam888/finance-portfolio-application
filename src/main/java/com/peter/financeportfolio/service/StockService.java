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

            Element companyNameDiv = document.select("#knowledge-finance-wholepage__entity-summary > div.aviV4d > g-card-section > div > g-card-section > div.OiIFo.knowledge-finance-wholepage__collapse-area-expand > div > div > span").first();
            if (companyNameDiv == null){
                companyName = stockCode;
            }
            else {

                companyName = companyNameDiv.text().trim();
            }


            Element stockPriceElement = document.select("#knowledge-finance-wholepage__entity-summary > div.aviV4d > g-card-section > div > g-card-section > div.wGt0Bc > div.PZPZlf > span:nth-child(1) > span > span.IsqQVc.NprOob.wT3VGc").first();

            if (stockPriceElement != null) {
                String stock_price = stockPriceElement.text().trim();
                String modified_stock_price = stock_price.replace(",", "");
                Float stockPrice = Float.valueOf(modified_stock_price);

                FetchedStockInfoDTO stockInfo = new FetchedStockInfoDTO(companyName, stockCode, stockPrice);

                return stockInfo;


            } else {
                String new_url = "https://www.google.com/search?q=" + stockCode;

                Document new_document = Jsoup.connect(new_url).get();


                String new_companyName;

                Element new_companyNameDiv = new_document.select("#knowledge-finance-wholepage__entity-summary > div.aviV4d > g-card-section > div > g-card-section > div.OiIFo.knowledge-finance-wholepage__collapse-area-expand > div > div > span").first();
                if (new_companyNameDiv == null){
                    new_companyName = stockCode;
                }
                else {

                    new_companyName = new_companyNameDiv.text().trim();
                }


                Element new_stockPriceElement = new_document.select("#knowledge-finance-wholepage__entity-summary > div.aviV4d > g-card-section > div > g-card-section > div.wGt0Bc > div.PZPZlf > span:nth-child(1) > span > span.IsqQVc.NprOob.wT3VGc").first();

                if (new_stockPriceElement != null) {
                    String stock_price = new_stockPriceElement.text().trim();
                    String modified_stock_price = stock_price.replace(",", "");
                    Float stockPrice = Float.valueOf(modified_stock_price);

                    FetchedStockInfoDTO stockInfo = new FetchedStockInfoDTO(new_companyName, stockCode, stockPrice);

                    return stockInfo;
                }
                else {
                    System.out.println("Unable to find the stock price element. Check the HTML structure.");
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }






}
