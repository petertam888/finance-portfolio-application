package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserDepositTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDepositTransactionsRepository extends JpaRepository<UserDepositTransactions, Long> {
}
