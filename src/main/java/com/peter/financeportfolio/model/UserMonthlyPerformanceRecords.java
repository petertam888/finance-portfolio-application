
package com.peter.financeportfolio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_monthly_performance_records")
public class UserMonthlyPerformanceRecords {

    @EmbeddedId
    private UserYearMonthRelation user_year_month_record;

    private Float inputted_amount;

    private Float final_amount;

    private Float performance;


    // Constructors, getters, and setters


    public UserYearMonthRelation getUser_year_month_record() {
        return user_year_month_record;
    }

    public void setUser_year_month_record(UserYearMonthRelation user_year_month_record) {
        this.user_year_month_record = user_year_month_record;
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

    public Float getPerformance() {
        return performance;
    }

    public void setPerformance(Float performance) {
        this.performance = performance;
    }
}
