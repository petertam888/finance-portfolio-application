
package com.peter.financeportfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_portfolio_yoy_records")
public class UserPortfolioYoYRecords {

    @EmbeddedId
    private UserYearRelation user_year_record;

    private Float inputted_amount;

    private Float final_amount;

    private Float qqq_amount;

    private Float cumulative_qqq_shares;

    private Float tqqq_amount;

    private Float cumulative_tqqq_shares;

    // Constructors, getters, and setters


    public UserYearRelation getUser_year_record() {
        return user_year_record;
    }

    public void setUser_year_record(UserYearRelation user_year_record) {
        this.user_year_record = user_year_record;
    }

    public Float getInputted_amount() {
        return inputted_amount;
    }

    public void setInputted_amount(Float inputted_amount) {
        this.inputted_amount = inputted_amount;
    }

    public Float getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(Float final_amount) {
        this.final_amount = final_amount;
    }

    public Float getQqq_amount() {
        return qqq_amount;
    }

    public void setQqq_amount(Float qqq_amount) {
        this.qqq_amount = qqq_amount;
    }

    public Float getCumulative_qqq_shares() {
        return cumulative_qqq_shares;
    }

    public void setCumulative_qqq_shares(Float cumulative_qqq_shares) {
        this.cumulative_qqq_shares = cumulative_qqq_shares;
    }

    public Float getTqqq_amount() {
        return tqqq_amount;
    }

    public void setTqqq_amount(Float tqqq_amount) {
        this.tqqq_amount = tqqq_amount;
    }

    public Float getCumulative_tqqq_shares() {
        return cumulative_tqqq_shares;
    }

    public void setCumulative_tqqq_shares(Float cumulative_tqqq_shares) {
        this.cumulative_tqqq_shares = cumulative_tqqq_shares;
    }
}
