package com.peter.financeportfolio.dto;

import com.peter.financeportfolio.model.UserStocks;

public class UserStockInfoDTO {
    private String stockCode;
    private Float stockPrice;
//
//    private String companyName;

    private Float shares;

//    public UserStockInfoDTO(String companyName, String stockCode, Float stockPrice, Integer shares){
//        this.companyName = companyName;
//        this.stockCode = stockCode;
//        this.stockPrice = stockPrice;
//        this.shares = shares;
//
//    }

    public UserStockInfoDTO(String stockCode, Float shares, Float stockPrice){
        this.stockCode = stockCode;
        this.stockPrice = stockPrice;
        this.shares = shares;

    }

//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

//    public Float getStockPrice() {
//        return stockPrice;
//    }
//
//    public void setStockPrice(Float stockPrice) {
//        this.stockPrice = stockPrice;
//    }

    public Float getShares() {
        return shares;
    }

    public void setShares(Float shares) {
        this.shares = shares;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }
}
