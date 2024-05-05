package com.peter.financeportfolio.model;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;


@Embeddable
public class UserLocalTimeRelation implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "transaction_time")
    private LocalDate transaction_time;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(LocalDate transaction_time) {
        this.transaction_time = transaction_time;
    }

// Getters, setters, equals, and hashcode methods
}
