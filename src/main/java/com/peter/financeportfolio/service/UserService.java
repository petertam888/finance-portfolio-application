package com.peter.financeportfolio.service;
import com.peter.financeportfolio.model.Transaction;
import com.peter.financeportfolio.repository.TransactionRepository;
import com.peter.financeportfolio.repository.UserTransactionRepository;
import com.peter.financeportfolio.dto.FetchedStockInfoDTO;
import com.peter.financeportfolio.dto.UserBriefPortfolioDTO;
import com.peter.financeportfolio.dto.UserStockInfoDTO;
import com.peter.financeportfolio.model.UserDeposit;
import com.peter.financeportfolio.model.UserStockCode;
import com.peter.financeportfolio.model.UserStocks;
import com.peter.financeportfolio.repository.UserStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.peter.financeportfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserStocksRepository userStocksRepository;

    private final UserTransactionRepository userTransactionRepository;

    private final StockService stockService;

    @Autowired
    public UserService(UserRepository userRepository, UserStocksRepository userStocksRepository, UserTransactionRepository userTransactionRepository , StockService stockService) {
        this.userRepository = userRepository;
        this.userStocksRepository = userStocksRepository;
        this.userTransactionRepository = userTransactionRepository;
        this.stockService = stockService;
    }

    public Map<String, Integer> addCashAmount(Long userId, Float cashAmount){


        UserDeposit userDepositOptional = userRepository.getUserDepositByUserId(userId);

        if (userDepositOptional != null) {
            Float userLatestDeposit =  cashAmount + userDepositOptional.getDeposit();
            userRepository.updateUserDepositByUserId(userId, userLatestDeposit);
        } else {
            UserDeposit userDeposit = new UserDeposit();
            userDeposit.setUser_id(userId);
            userDeposit.setDeposit(cashAmount);
            userRepository.save(userDeposit);
        }

        Map<String, Integer> acknowledgement = new HashMap<>();

        acknowledgement.put("statusCode", 200);


        return acknowledgement;
    }
    public UserBriefPortfolioDTO getUserBriefPortfolio(Long userId){
    //return owned stock list ( including stock code, and shares, current cost), total account value, cash amount
        Float CurrentStockAmount = 0.0f;

        List<UserStocks> UserStocksList = userStocksRepository.getUserStockByUserId(userId);
        List<UserStockInfoDTO> StockInfoList = new ArrayList<>();
        for (UserStocks userStocks : UserStocksList) {
            UserStockCode UserStockCode = userStocks.getUser_stock_code();
            String stockCode = UserStockCode.getStockCode();
            FetchedStockInfoDTO stockInfo = stockService.fetchStockInformation(stockCode);
            Float CurrentStockPrice = stockInfo.getStockPrice();
            CurrentStockAmount += userStocks.getShares()*CurrentStockPrice;
            UserStockInfoDTO userStockInfo = new UserStockInfoDTO(stockCode, userStocks.getShares(), CurrentStockPrice);
            StockInfoList.add(userStockInfo);
        }

        UserDeposit userDeposit = userRepository.getUserDepositByUserId(userId);
        Float cash = userDeposit.getDeposit();
        if (cash != null) {
            Float CurrentAccountAmount = cash + CurrentStockAmount;
        }
        else
            cash = 0.0f;
            Float CurrentAccountAmount = cash + CurrentStockAmount;

        UserBriefPortfolioDTO userBriefPortfolio = new UserBriefPortfolioDTO(StockInfoList, CurrentAccountAmount, cash);



        return userBriefPortfolio;
    }

    public List<Transaction> getUserTransactionRecords(Long userId){

        List<Transaction> UserTransactionRecords = userTransactionRepository.getUserTransactionRecordsByUserId(userId);




        return UserTransactionRecords;
    }
}
