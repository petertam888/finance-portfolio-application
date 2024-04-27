
package com.peter.financeportfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "user_deposit_transactions")
public class UserDepositTransactions {

    @Id
    private Long user_id;

    private Float amount;

    private LocalDate transaction_time;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getTransaction_time() {
        return transaction_time;
    }

    public void setTransaction_time(LocalDate transaction_time) {
        this.transaction_time = transaction_time;
    }
}
