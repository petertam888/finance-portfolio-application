package com.peter.financeportfolio.service;

import com.peter.financeportfolio.dto.FetchedStockInfoDTO;
import com.peter.financeportfolio.dto.UserProfitInfoDTO;
import com.peter.financeportfolio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class AnalysisService {

    private final UserService userService;

    private final StockService stockService;
    private final UserDepositTransactionsRepository userDepositTransactionsRepository;

    private final UserMonthlyInvestmentForQQQAndTQQQRepository userMonthlyInvestmentForQQQAndTQQQRepository;

    @Autowired
    public AnalysisService(UserService userService, StockService stockService, UserDepositTransactionsRepository userDepositTransactionsRepository,UserMonthlyInvestmentForQQQAndTQQQRepository userMonthlyInvestmentForQQQAndTQQQRepository ) {
        this.userService = userService;
        this.stockService = stockService;
        this.userDepositTransactionsRepository = userDepositTransactionsRepository;
        this.userMonthlyInvestmentForQQQAndTQQQRepository = userMonthlyInvestmentForQQQAndTQQQRepository;
    }

    public UserProfitInfoDTO getAccountProfitsInfo(Long userId){
        Float totalSumOfUserDeposit = userDepositTransactionsRepository.getUserTotalAmount(userId);
        Float userCurrentAccountAmount = userService.getUserBriefPortfolio(userId).getTotalAccountAmount();
        Float userActualProfit = userCurrentAccountAmount-totalSumOfUserDeposit;
        Float totalActualProfit = (float)userActualProfit/totalSumOfUserDeposit;

        Float totalSharesOfUserQQQ = userMonthlyInvestmentForQQQAndTQQQRepository.getUserTotalQQQShares(userId);
        Float totalSharesOfUserTQQQ = userMonthlyInvestmentForQQQAndTQQQRepository.getUserTotalTQQQShares(userId);


        FetchedStockInfoDTO QQQStockInfo = stockService.fetchStockInformation("QQQ");
        Float QQQ_current_price = QQQStockInfo.getStockPrice();
        Float userCurrentQQQAmount = QQQ_current_price*totalSharesOfUserQQQ;
        Float userActualQQQProfit = userCurrentQQQAmount-totalSumOfUserDeposit;
        Float totalActualQQQProfit = (float)userActualQQQProfit/totalSumOfUserDeposit;

        FetchedStockInfoDTO TQQQStockInfo = stockService.fetchStockInformation("TQQQ");
        Float TQQQ_current_price = TQQQStockInfo.getStockPrice();
        Float userCurrentTQQQAmount = TQQQ_current_price*totalSharesOfUserTQQQ;
        Float userActualTQQQProfit = userCurrentTQQQAmount-totalSumOfUserDeposit;
        Float totalActualTQQQProfit = (float)userActualTQQQProfit/totalSumOfUserDeposit;


        UserProfitInfoDTO userProfitInfoDTO = new UserProfitInfoDTO(totalActualProfit, totalActualQQQProfit, totalActualTQQQProfit);

        return userProfitInfoDTO;
    }





}
