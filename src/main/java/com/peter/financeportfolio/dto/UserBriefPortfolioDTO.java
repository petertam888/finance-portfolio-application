package com.peter.financeportfolio.dto;

import com.peter.financeportfolio.dto.UserStockInfoDTO;

import java.util.List;

public class UserBriefPortfolioDTO {
    private List<UserStockInfoDTO> stocksInfo;
    private Float totalAccountAmount;
    private Float cashAmount;

    public UserBriefPortfolioDTO(List<UserStockInfoDTO> stocksInfo, Float totalAccountAmount, Float cashAmount){
        this.stocksInfo = stocksInfo;
        this.totalAccountAmount = totalAccountAmount;
        this.cashAmount = cashAmount;

    }

    public List<UserStockInfoDTO> getStocksInfo() {
        return stocksInfo;
    }

    public void setStocksInfo(List<UserStockInfoDTO> stocksInfo) {
        this.stocksInfo = stocksInfo;
    }

    public Float getTotalAccountAmount() {
        return totalAccountAmount;
    }

    public void setTotalAccountAmount(Float totalAccountAmount) {
        this.totalAccountAmount = totalAccountAmount;
    }

    public Float getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Float cashAmount) {
        this.cashAmount = cashAmount;
    }
}
