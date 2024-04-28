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

}
