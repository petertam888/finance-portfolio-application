package com.peter.financeportfolio.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import jakarta.persistence.Column;



@Embeddable
public class UserStockCode implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stock_code")
    private String stockCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    // Getters, setters, equals, and hashcode methods
}
