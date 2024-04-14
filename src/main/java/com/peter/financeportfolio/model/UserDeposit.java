
package com.peter.financeportfolio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_deposit")
public class UserDeposit {

    @Id
    private Long user_id;

    private Float deposit;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Float getDeposit() {
        return deposit;
    }

    public void setDeposit(Float deposit) {
        this.deposit = deposit;
    }

    // Constructors, getters, and setters
}
