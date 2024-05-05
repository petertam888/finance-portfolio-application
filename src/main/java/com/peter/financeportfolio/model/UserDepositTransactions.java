
package com.peter.financeportfolio.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "user_deposit_transactions")
public class UserDepositTransactions {

    @EmbeddedId
    private UserLocalTimeRelation user_transaction_time;

    private Float amount;

    public UserLocalTimeRelation getUser_transaction_time() {
        return user_transaction_time;
    }

    public void setUser_transaction_time(UserLocalTimeRelation user_transaction_time) {
        this.user_transaction_time = user_transaction_time;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
