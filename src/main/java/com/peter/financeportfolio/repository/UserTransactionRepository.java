package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM user_transactions WHERE user_id = :userId", nativeQuery = true)
    List<Transaction> getUserTransactionRecordsByUserId(@Param("userId") Long userId);



}
