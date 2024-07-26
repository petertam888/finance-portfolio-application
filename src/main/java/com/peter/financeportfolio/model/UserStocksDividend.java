
package com.peter.financeportfolio.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "user_stocks_dividend")
public class UserStocksDividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_dividend_transaction_id")
    private Long dividendTransactionId;

    @Column(name = "transaction_time")
    private LocalDate time;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "dividend_by_shares", nullable = true)
    private Float dividendByShares;

    @Column(name = "dividend_by_cash", nullable = true)
    private Float dividendByCash;

    public Long getDividendTransactionId() {
        return dividendTransactionId;
    }

    public void setDividendTransactionId(Long dividendTransactionId) {
        this.dividendTransactionId = dividendTransactionId;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Float getDividendByShares() {
        return dividendByShares;
    }

    public void setDividendByShares(Float dividendByShares) {
        this.dividendByShares = dividendByShares;
    }

    public Float getDividendByCash() {
        return dividendByCash;
    }

    public void setDividendByCash(Float dividendByCash) {
        this.dividendByCash = dividendByCash;
    }
}
