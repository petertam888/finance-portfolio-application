package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserDeposit;
import com.peter.financeportfolio.model.UserDepositTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDepositTransactionsRepository extends JpaRepository<UserDepositTransactions, Long> {
    @Query(value = "SELECT SUM(amount) FROM user_deposit_transactions WHERE user_id = :userId", nativeQuery = true)
    Float getUserTotalAmount(@Param("userId") Long userId);

    @Query(value = "SELECT DATEDIFF(NOW(), initial_date) AS total_days " +
            "FROM (SELECT MIN(transaction_time) AS initial_date " +
            "FROM user_deposit_transactions " +
            "WHERE user_id = :userId) AS initial_date_subquery", nativeQuery = true)
    Integer getDaysSinceInitialTransaction(@Param("userId") Long userId);




}
