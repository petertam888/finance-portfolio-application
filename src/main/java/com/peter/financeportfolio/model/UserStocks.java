
package com.peter.financeportfolio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "user_stocks")
public class UserStocks {

    @EmbeddedId
    private UserStockCodeRelation user_stock_code;

    private Float shares;

    private Float cost;

    public UserStockCodeRelation getUser_stock_code() {
        return user_stock_code;
    }

    public void setUser_stock_code(UserStockCodeRelation user_stock_code) {
        this.user_stock_code = user_stock_code;
    }

    public Float getShares() {
        return shares;
    }

    public void setShares(Float shares) {
        this.shares = shares;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }
    // Constructors, getters, and setters
}
