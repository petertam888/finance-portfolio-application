package com.peter.financeportfolio.service;

import com.peter.financeportfolio.model.Transaction;
import com.peter.financeportfolio.repository.TransactionRepository;
import com.sun.net.httpserver.Authenticator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Map<String, Integer> buyStocksWithCurrentPrice(Long userId, String stockCode, Float stockPrice, Integer shares){
        Transaction transaction = new Transaction();
        Map<String, Integer> acknowledgement = new HashMap<>();

        LocalTime now = LocalTime.now();

        transaction.setTime(now);
        transaction.setUserId(userId);
        transaction.setStockCode(stockCode);
        transaction.setStockPrice(stockPrice);
        transaction.setShares(shares);

        transactionRepository.save(transaction);
        acknowledgement.put("statusCode", 200);


        return acknowledgement;
    }



//    public Map<String, String> buyStock(String stockSymbol, Integer numberOfStock){
//        return stockRepository.getAllStocks();
//    }




}
