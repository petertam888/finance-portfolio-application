package com.peter.financeportfolio.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "user_transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "transaction_time")
    private LocalTime time;

    @Column(name = "user_id")
    private Long userId;


    @Column(name = "stock_code")
    private String stockCode;


    @Column(name = "stock_price")
    private Float stockPrice;

    @Column(name = "shares")
    private Integer shares;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public void setStockPrice(Float stockPrice) {
        this.stockPrice = stockPrice;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getUserId() {
        return userId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public Float getStockPrice() {
        return stockPrice;
    }

    public Integer getShares() {
        return shares;
    }
}

