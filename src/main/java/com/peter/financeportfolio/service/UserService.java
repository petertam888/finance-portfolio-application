package com.peter.financeportfolio.service;
import com.peter.financeportfolio.model.*;
import com.peter.financeportfolio.repository.*;
import com.peter.financeportfolio.dto.FetchedStockInfoDTO;
import com.peter.financeportfolio.dto.UserBriefPortfolioDTO;
import com.peter.financeportfolio.dto.UserStockInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserStocksRepository userStocksRepository;

    private final UserTransactionRepository userTransactionRepository;

    private final UserMonthlyPerformanceRecordsRepository userMonthlyPerformanceRecordsRepository;

    private final UserDepositTransactionsRepository userDepositTransactionsRepository;

    private final UserMonthlyInvestmentForQQQAndTQQQRepository userMonthlyInvestmentForQQQAndTQQQRepository;

    private final StockService stockService;

    @Autowired
    public UserService(UserRepository userRepository, UserStocksRepository userStocksRepository, UserTransactionRepository userTransactionRepository , UserDepositTransactionsRepository userDepositTransactionsRepository,StockService stockService, UserMonthlyInvestmentForQQQAndTQQQRepository userMonthlyInvestmentForQQQAndTQQQRepository, UserMonthlyPerformanceRecordsRepository userMonthlyPerformanceRecordsRepository) {
        this.userRepository = userRepository;
        this.userMonthlyPerformanceRecordsRepository = userMonthlyPerformanceRecordsRepository;
        this.userDepositTransactionsRepository = userDepositTransactionsRepository;
        this.userStocksRepository = userStocksRepository;
        this.userTransactionRepository = userTransactionRepository;
        this.userMonthlyInvestmentForQQQAndTQQQRepository = userMonthlyInvestmentForQQQAndTQQQRepository;
        this.stockService = stockService;
    }

    public Map<String, Integer> addCashAmount(Long userId, Float cashAmount){


        UserDeposit userDepositOptional = userRepository.getUserDepositByUserId(userId);

        if (userDepositOptional != null) {
            Float userLatestDeposit =  cashAmount + userDepositOptional.getDeposit();
            userRepository.updateUserDepositByUserId(userId, userLatestDeposit);
            UserDepositTransactions userDepositTransaction = new UserDepositTransactions();
            UserLocalTimeRelation userLocalTimeRelation = new UserLocalTimeRelation();
            LocalDate date = LocalDate.now();
            userLocalTimeRelation.setUserId(userId);
            userLocalTimeRelation.setTransaction_time(date);
            userDepositTransaction.setUser_transaction_time(userLocalTimeRelation);
            userDepositTransaction.setAmount(cashAmount);
            userDepositTransactionsRepository.save(userDepositTransaction);
        } else {
            UserDeposit userDeposit = new UserDeposit();
            userDeposit.setUser_id(userId);
            userDeposit.setDeposit(cashAmount);
            userRepository.save(userDeposit);
            UserDepositTransactions userDepositTransaction = new UserDepositTransactions();
            UserLocalTimeRelation userLocalTimeRelation = new UserLocalTimeRelation();
            LocalDate date = LocalDate.now();
            userLocalTimeRelation.setUserId(userId);
            userLocalTimeRelation.setTransaction_time(date);
            userDepositTransaction.setAmount(cashAmount);
            userDepositTransaction.setUser_transaction_time(userLocalTimeRelation);
            userDepositTransactionsRepository.save(userDepositTransaction);
        }

        Map<String, Integer> result = this.addMonthlyInvestmentForQQQAndTQQQEstimatedRecord(cashAmount, userId);


        // FetchedStockInfoDTO TQQQStockInfo = stockService.fetchStockInformation("TQQQ");
        // Float TQQQ_buy_price = TQQQStockInfo.getStockPrice();
        // FetchedStockInfoDTO QQQStockInfo = stockService.fetchStockInformation("QQQ");
        // Float QQQ_buy_price = QQQStockInfo.getStockPrice();

        // Float TQQQ_shares = (float)cashAmount/TQQQ_buy_price ;
        // Float QQQ_shares = (float)cashAmount/QQQ_buy_price ;

        // UserYearMonthRelation userYearMonthRelation = new UserYearMonthRelation();
        // userYearMonthRelation.setUserId(userId);
        // userYearMonthRelation.setYear(LocalDate.now().getYear());
        // userYearMonthRelation.setMonth(LocalDate.now().getMonthValue());

        // UserMonthlyInvestmentForQQQAndTQQQRecords userMonthlyInvestmentForQQQAndTQQQRecord = new UserMonthlyInvestmentForQQQAndTQQQRecords();
        // userMonthlyInvestmentForQQQAndTQQQRecord.setQQQ_shares(QQQ_shares);
        // userMonthlyInvestmentForQQQAndTQQQRecord.setTQQQ_shares(TQQQ_shares);
        // userMonthlyInvestmentForQQQAndTQQQRecord.setQQQ_buy_price(QQQ_buy_price);
        // userMonthlyInvestmentForQQQAndTQQQRecord.setTQQQ_buy_price(TQQQ_buy_price);
        // userMonthlyInvestmentForQQQAndTQQQRecord.setUser_year_month_record(userYearMonthRelation);
        // userMonthlyInvestmentForQQQAndTQQQRepository.save(userMonthlyInvestmentForQQQAndTQQQRecord);

        Map<String, Integer> acknowledgement = new HashMap<>();
        if (result.get("statusCode") == 200){
            acknowledgement.put("statusCode", 200);
        }
        else{
            acknowledgement.put("statusCode", 500);
        }


        return acknowledgement;
    }

    public Map<String, Integer> addMonthlyInvestmentForQQQAndTQQQEstimatedRecord(Float cashAmount, Long userId){

        FetchedStockInfoDTO TQQQStockInfo = stockService.fetchStockInformation("TQQQ");
        Float TQQQ_buy_price = TQQQStockInfo.getStockPrice();
        FetchedStockInfoDTO QQQStockInfo = stockService.fetchStockInformation("QQQ");
        Float QQQ_buy_price = QQQStockInfo.getStockPrice();


        Float TQQQ_shares = (float)cashAmount/TQQQ_buy_price ;
        Float QQQ_shares = (float)cashAmount/QQQ_buy_price ;

        UserYearMonthRelation userYearMonthRelation = new UserYearMonthRelation();
        userYearMonthRelation.setUserId(userId);
        userYearMonthRelation.setYear(LocalDate.now().getYear());
        userYearMonthRelation.setMonth(LocalDate.now().getMonthValue());

        UserMonthlyInvestmentForQQQAndTQQQRecords userMonthlyInvestmentForQQQAndTQQQRecord = new UserMonthlyInvestmentForQQQAndTQQQRecords();
        userMonthlyInvestmentForQQQAndTQQQRecord.setQQQ_shares(QQQ_shares);
        userMonthlyInvestmentForQQQAndTQQQRecord.setTQQQ_shares(TQQQ_shares);
        userMonthlyInvestmentForQQQAndTQQQRecord.setQQQ_buy_price(QQQ_buy_price);
        userMonthlyInvestmentForQQQAndTQQQRecord.setTQQQ_buy_price(TQQQ_buy_price);
        userMonthlyInvestmentForQQQAndTQQQRecord.setUser_year_month_record(userYearMonthRelation);
        userMonthlyInvestmentForQQQAndTQQQRepository.save(userMonthlyInvestmentForQQQAndTQQQRecord);

        Map<String, Integer> acknowledgement = new HashMap<>();

        acknowledgement.put("statusCode", 200);


        return acknowledgement;


    }

    public Map<String, Integer> addMonthlyInvestmentRecord(Long userId){
       Integer currentMonth = LocalDate.now().minusMonths(1).getMonthValue();
       Integer currentYear = LocalDate.now().minusMonths(1).getYear();
       Integer monthOfLastRecord = LocalDate.now().minusMonths(2).getMonthValue();
       Integer yearOfLastRecord = LocalDate.now().minusMonths(2).getYear();
       Float finalAmount =  this.getUserBriefPortfolio(userId).getTotalAccountAmount();
        if (finalAmount == null){
            finalAmount = 0.0f;
        }
       Float inputtedAmount = userMonthlyPerformanceRecordsRepository.getFinalAmountForUser(userId, monthOfLastRecord, yearOfLastRecord);
       if (inputtedAmount == null){
           inputtedAmount = finalAmount;
       }
       Float monthlyProfit = finalAmount-inputtedAmount;
       Float monthlyPerformance = (float)monthlyProfit/inputtedAmount;
       if (monthlyPerformance.isNaN()){
           monthlyPerformance = 0.0f;
       }
       UserYearMonthRelation userInfo = new UserYearMonthRelation();
       UserMonthlyPerformanceRecords userMonthlyPerformanceRecords = new UserMonthlyPerformanceRecords();
       userInfo.setUserId(userId);
       userInfo.setYear(currentYear);
       userInfo.setMonth(currentMonth);
       userMonthlyPerformanceRecords.setUser_year_month_record(userInfo);
       userMonthlyPerformanceRecords.setFinal_amount(finalAmount);
       userMonthlyPerformanceRecords.setInputted_amount(inputtedAmount);
       userMonthlyPerformanceRecords.setPerformance(monthlyPerformance);

       userMonthlyPerformanceRecordsRepository.save(userMonthlyPerformanceRecords);


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
            UserStockCodeRelation UserStockCodeRelation = userStocks.getUser_stock_code();
            String stockCode = UserStockCodeRelation.getStockCode();
            FetchedStockInfoDTO stockInfo = stockService.fetchStockInformation(stockCode);
            Float CurrentStockPrice = stockInfo.getStockPrice();
            CurrentStockAmount += userStocks.getShares()*CurrentStockPrice;
            UserStockInfoDTO userStockInfo = new UserStockInfoDTO(stockCode, userStocks.getShares(), CurrentStockPrice);
            StockInfoList.add(userStockInfo);
        }

        UserDeposit userDeposit = userRepository.getUserDepositByUserId(userId);
        if (userDeposit == null){
            UserBriefPortfolioDTO userBriefPortfolio = new UserBriefPortfolioDTO(null, 0.0f, 0.0f);
            return userBriefPortfolio;
        }
        else{
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


    }

    public List<Transaction> getUserTransactionRecords(Long userId){

        List<Transaction> UserTransactionRecords = userTransactionRepository.getUserTransactionRecordsByUserId(userId);




        return UserTransactionRecords;
    }
}
