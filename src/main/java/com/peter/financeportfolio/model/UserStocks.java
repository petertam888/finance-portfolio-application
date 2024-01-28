package com.peter.financeportfolio.model;

public class UserStocks {

    private Long user_id;
    private Long stock_code;
    private int number;

    public UserStocks(Long user_id, Long stock_code, int number) {
        this.user_id = user_id;
        this.stock_code = stock_code;
        this.number = number;
    }

    // Constructors, getters, setters...
}
