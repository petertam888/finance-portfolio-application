package com.peter.financeportfolio.model;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class UserYearRelation implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "\"year\"")
    private Integer year;

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

// Getters, setters, equals, and hashcode methods
}
