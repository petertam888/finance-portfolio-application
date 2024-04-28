
package com.peter.financeportfolio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_monthly_qqq_and_tqqq_investment_records")
public class UserMonthlyInvestmentForQQQAndTQQQRecords {

    @EmbeddedId
    private UserYearMonthRelation user_year_month_record;

    private Float TQQQ_shares;

    private Float TQQQ_buy_price;

    private Float QQQ_shares;

    private Float QQQ_buy_price;


    // Constructors, getters, and setters

    public UserYearMonthRelation getUser_year_month_record() {
        return user_year_month_record;
    }

    public void setUser_year_month_record(UserYearMonthRelation user_year_month_record) {
        this.user_year_month_record = user_year_month_record;
    }

    public Float getTQQQ_shares() {
        return TQQQ_shares;
    }

    public void setTQQQ_shares(Float TQQQ_shares) {
        this.TQQQ_shares = TQQQ_shares;
    }

    public Float getTQQQ_buy_price() {
        return TQQQ_buy_price;
    }

    public void setTQQQ_buy_price(Float TQQQ_buy_price) {
        this.TQQQ_buy_price = TQQQ_buy_price;
    }

    public Float getQQQ_shares() {
        return QQQ_shares;
    }

    public void setQQQ_shares(Float QQQ_shares) {
        this.QQQ_shares = QQQ_shares;
    }

    public Float getQQQ_buy_price() {
        return QQQ_buy_price;
    }

    public void setQQQ_buy_price(Float QQQ_buy_price) {
        this.QQQ_buy_price = QQQ_buy_price;
    }
}
