package com.peter.financeportfolio.dto;

public class UserProfitInfoDTO {
    private Float accountProfit;
    private Float estimatedQQQProfit;

    private Float estimatedTQQQProfit;

    public UserProfitInfoDTO(Float accountProfit, Float estimatedQQQProfit, Float estimatedTQQQProfit){
        this.accountProfit = accountProfit;
        this.estimatedQQQProfit = estimatedQQQProfit;
        this.estimatedTQQQProfit = estimatedTQQQProfit;

    }

    public Float getAccountProfit() {
        return accountProfit;
    }

    public void setAccountProfit(Float accountProfit) {
        this.accountProfit = accountProfit;
    }

    public Float getEstimatedQQQProfit() {
        return estimatedQQQProfit;
    }

    public void setEstimatedQQQProfit(Float estimatedQQQProfit) {
        this.estimatedQQQProfit = estimatedQQQProfit;
    }

    public Float getEstimatedTQQQProfit() {
        return estimatedTQQQProfit;
    }

    public void setEstimatedTQQQProfit(Float estimatedTQQQProfit) {
        this.estimatedTQQQProfit = estimatedTQQQProfit;
    }
}
