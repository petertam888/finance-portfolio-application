package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserMonthlyPerformanceRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMonthlyPerformanceRecordsRepository extends JpaRepository<UserMonthlyPerformanceRecords, Long> {
    @Query(value = "SELECT final_amount FROM user_monthly_performance_records WHERE user_id = :userId AND month = :month AND year = :year", nativeQuery = true)
    Float getFinalAmountForUser(@Param("userId") Long userId, @Param("month") Integer month, @Param("year") Integer year);
}
