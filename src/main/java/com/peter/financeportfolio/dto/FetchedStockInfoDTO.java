package com.peter.financeportfolio.dto;

public class FetchedStockInfoDTO {
    private String companyName;
    private String stockCode;
    private Float stockPrice;

    public FetchedStockInfoDTO(String companyName, String stockCode, Float stockPrice){
        this.companyName = companyName;
        this.stockCode = stockCode;
        this.stockPrice = stockPrice;

    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }
}
