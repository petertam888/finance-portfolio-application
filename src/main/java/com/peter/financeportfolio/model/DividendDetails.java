package com.peter.financeportfolio.model;

public class DividendDetails {
    private Float dividendByShares;
    private Float dividendByCash;

    public DividendDetails() {}

    public DividendDetails(Float dividendByShares, Float dividendByCash) {
        this.dividendByShares = dividendByShares;
        this.dividendByCash = dividendByCash;
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

