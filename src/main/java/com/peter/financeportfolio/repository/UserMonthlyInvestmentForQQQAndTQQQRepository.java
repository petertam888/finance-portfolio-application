package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserMonthlyInvestmentForQQQAndTQQQRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMonthlyInvestmentForQQQAndTQQQRepository extends JpaRepository<UserMonthlyInvestmentForQQQAndTQQQRecords, Long> {
    @Query(value = "SELECT SUM(TQQQ_shares) FROM user_monthly_qqq_and_tqqq_investment_records WHERE user_id = :userId", nativeQuery = true)
    Float getUserTotalTQQQShares(@Param("userId") Long userId);

    @Query(value = "SELECT SUM(QQQ_shares) FROM user_monthly_qqq_and_tqqq_investment_records WHERE user_id = :userId", nativeQuery = true)
    Float getUserTotalQQQShares(@Param("userId") Long userId);
}
