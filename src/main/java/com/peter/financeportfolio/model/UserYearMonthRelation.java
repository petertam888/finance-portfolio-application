package com.peter.financeportfolio.model;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class UserYearMonthRelation implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "\"year\"")
    private Integer year;

    @Column(name = "\"month\"")
    private Integer month;


    // Getters, setters, equals, and hashcode methods
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
