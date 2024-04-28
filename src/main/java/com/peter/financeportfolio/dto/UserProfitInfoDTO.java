package com.peter.financeportfolio.dto;

public class UserProfitInfoDTO {
    private Float accountProfit;
    private Float estimatedQQQProfit;

    private Float estimatedTQQQProfit;

    private Float yoy;

    public UserProfitInfoDTO(Float accountProfit, Float estimatedQQQProfit, Float estimatedTQQQProfit, Float yoy){
        this.accountProfit = accountProfit;
        this.estimatedQQQProfit = estimatedQQQProfit;
        this.estimatedTQQQProfit = estimatedTQQQProfit;
        this.yoy = yoy;

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

    public Float getYoy() {
        return yoy;
    }

    public void setYoy(Float yoy) {
        this.yoy = yoy;
    }
}
