package com.peter.financeportfolio;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;

import java.io.IOException;

public class test{
    public static void main(String[] args) {
        try {
            // Replace "AAPL" with the stock symbol you want to fetch
            String stockSymbol = "TSLA";
            String url = "http://finance.yahoo.com/quote/" + stockSymbol;


            // Fetch the HTML content of the webpage
//            Document document = Jsoup.connect(url).get();
//
//            String title = document.title();
//
//            System.out.println(title);

            Document document = Jsoup.connect(url).get();


//            Element companyNameDiv = document.select("div.D(ib) h1.Fz\\(18px\\)").first();
            // in html file, the http content is: <h1 class="D(ib) Fz(18px)">{Apple Inc. (AAPL)}</h1>
            Element companyNameDiv = document.select("h1.D\\(ib\\).Fz\\(18px\\)").first();

            if (companyNameDiv != null) {

                    // Get the text content from the stock price element
                    String companyName = companyNameDiv.text().trim();

                    // Display the extracted stock price
                    System.out.println("Company Name: " + companyName);
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
                    String rowstockPrice = stockPriceElement.text();
                    String stockPrice = stockPriceElement.text().trim();

                    // Display the extracted stock price
                    System.out.println("Symbol: " + stockSymbol);
                    System.out.println("Stock Price: " + stockPrice);
                } else {
                    System.out.println("Unable to find the stock price element. Check the HTML structure.");
                }
            } else {
                System.out.println("Unable to find the target div. Check the HTML structure.");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
