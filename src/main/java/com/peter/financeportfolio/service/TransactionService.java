package com.peter.financeportfolio.service;

import com.peter.financeportfolio.model.*;
import com.peter.financeportfolio.repository.DividendRepository;
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

    private final DividendRepository dividendRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserStocksRepository userStocksRepository, UserRepository userRepository, DividendRepository dividendRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.userStocksRepository = userStocksRepository;
        this.dividendRepository = dividendRepository;
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

    public Map<String, Integer> addDividendRecord(LocalDate transactionDate, Long userId, String stockCode, DividendDetails dividendDetails){

        UserStocksDividend userStocksDividend = new UserStocksDividend();
        Map<String, Integer> acknowledgement = new HashMap<>();

        userStocksDividend.setTime(transactionDate);
        userStocksDividend.setUserId(userId);
        userStocksDividend.setStockCode(stockCode);
        Float dividendByShares = dividendDetails.getDividendByShares();
        Float dividendByCash = dividendDetails.getDividendByCash();
        userStocksDividend.setDividendByShares(dividendByShares);
        userStocksDividend.setDividendByCash(dividendByCash);

        dividendRepository.save(userStocksDividend);

        Optional<UserStocks> userStocksOptional = userStocksRepository.getUserStockByUserIdAndStockCode(userId, stockCode);

        UserStocks stock = userStocksOptional.get();
        if (dividendByShares != null){
            Float totalCost = stock.getCost()*stock.getShares();
            Float latest_shares = stock.getShares() + dividendByShares;
            Float updatedCost = (totalCost)/(latest_shares);

            userStocksRepository.updateUserStockByUserIdAndStockCode(userId, updatedCost, latest_shares, stockCode);
        }
        else{
            Float totalCost = stock.getCost()*stock.getShares();
            Float shares = stock.getShares();
            Float updatedCost = (totalCost-dividendByCash)/(shares);
            UserDeposit userDepositOptional = userRepository.getUserDepositByUserId(userId);
            Float userLatestDeposit =  dividendByCash + userDepositOptional.getDeposit();
            userRepository.updateUserDepositByUserId(userId, userLatestDeposit);
            userStocksRepository.updateUserStockByUserIdAndStockCode(userId, updatedCost, shares, stockCode);
        }
        acknowledgement.put("statusCode", 200);


        return acknowledgement;
    }






}
