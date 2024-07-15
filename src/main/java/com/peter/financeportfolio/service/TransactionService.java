package com.peter.financeportfolio.service;

import com.peter.financeportfolio.model.Transaction;
import com.peter.financeportfolio.model.UserStockCodeRelation;
import com.peter.financeportfolio.model.UserStocks;
import com.peter.financeportfolio.repository.TransactionRepository;
import com.peter.financeportfolio.repository.UserRepository;
import com.peter.financeportfolio.repository.UserStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserStocksRepository userStocksRepository;

    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserStocksRepository userStocksRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.userStocksRepository = userStocksRepository;
    }

    public Map<String, Integer> buyStocksWithCurrentPrice(Long userId, String stockCode, Float stockPrice, Float shares){
        Transaction transaction = new Transaction();
        Map<String, Integer> acknowledgement = new HashMap<>();

        LocalDate now = LocalDate.now();

        transaction.setTime(now);
        transaction.setUserId(userId);
        transaction.setStockCode(stockCode);
        transaction.setStockPrice(stockPrice);
        transaction.setShares(shares);

        transactionRepository.save(transaction);

        Optional<UserStocks> userStocksOptional = userStocksRepository.getUserStockByUserIdAndStockCode(userId, stockCode);
        if (userStocksOptional.isPresent()){
            UserStocks stock = userStocksOptional.get();
            Float totalCost = stock.getCost()*stock.getShares();
            Float transactionCost = stockPrice*shares;
            Float updatedCost = (totalCost+transactionCost)/(shares+stock.getShares());
            userStocksRepository.updateUserStockByUserIdAndStockCode(userId, updatedCost, stock.getShares()+shares, stockCode);
        }else{
            UserStocks userStocks = new UserStocks();
            UserStockCodeRelation userStockCodeRelation = new UserStockCodeRelation();
            userStockCodeRelation.setUserId(userId);
            userStockCodeRelation.setStockCode(stockCode);
            userStocks.setUser_stock_code(userStockCodeRelation);
            userStocks.setCost(stockPrice);
            userStocks.setShares(shares);
            userStocksRepository.save(userStocks);
        }
        acknowledgement.put("statusCode", 200);


        return acknowledgement;
    }


    public Map<String, Integer> addTransactionRecord(LocalDate transactionDate, Long userId, String stockCode, Float stockPrice, Float shares){
        Transaction transaction = new Transaction();
        Map<String, Integer> acknowledgement = new HashMap<>();

        transaction.setTime(transactionDate);
        transaction.setUserId(userId);
        transaction.setStockCode(stockCode);
        transaction.setStockPrice(stockPrice);
        transaction.setShares(shares);

        transactionRepository.save(transaction);
        Optional<UserStocks> userStocksOptional = userStocksRepository.getUserStockByUserIdAndStockCode(userId, stockCode);
        if (userStocksOptional.isPresent()){
            UserStocks stock = userStocksOptional.get();
            Float totalCost = stock.getCost()*stock.getShares();
            Float transactionCost = stockPrice*shares;
            Float updatedCost;
            if (shares+stock.getShares()==0){
                updatedCost = (totalCost + transactionCost);
            }
            else{
                updatedCost = (totalCost+transactionCost)/(shares+stock.getShares());
            }
            userStocksRepository.updateUserStockByUserIdAndStockCode(userId, updatedCost, stock.getShares()+shares, stockCode);
            userRepository.updateUserDepositByUserIdAndTransactionCost(userId, transactionCost);

        }else{
            UserStocks userStocks = new UserStocks();
            UserStockCodeRelation userStockCodeRelation = new UserStockCodeRelation();
            userStockCodeRelation.setUserId(userId);
            userStockCodeRelation.setStockCode(stockCode);
            userStocks.setUser_stock_code(userStockCodeRelation);
            userStocks.setCost(stockPrice);
            userStocks.setShares(shares);
            userStocksRepository.save(userStocks);
            userRepository.updateUserDepositByUserIdAndTransactionCost(userId, (stockPrice*shares));
        }
        acknowledgement.put("statusCode", 200);


        return acknowledgement;
    }




}
