package com.peter.financeportfolio.model;

public class UserStocks {

    private Long userId;
    private String username;
    private String stockCode;
    private int stockNumber;

    public UserStocks(Long userId, String username, String stockCode, int stockNumber) {
        this.userId = userId;
        this.username = username;
        this.stockCode = stockCode;
        this.stockNumber = stockNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    // Optional: Override toString() method for better representation
    @Override
    public String toString() {
        return "UserStocks{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", stockNumber=" + stockNumber +
                '}';
    }
}


