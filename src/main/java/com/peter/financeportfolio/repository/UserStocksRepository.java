package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserStocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStocksRepository extends JpaRepository<UserStocks, Long> {
    @Query(value = "SELECT * FROM user_stocks WHERE user_id = :userId and stock_code = :stockCode", nativeQuery = true)
    UserStocks getUserStockByUserIdAndStockCode(@Param("userId") Long userId, @Param("stockCode") String stockCode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_stocks SET cost = :newCost, shares = :newShares WHERE user_id = :userId and stock_code = :stockCode", nativeQuery = true)
    void updateUserStockByUserIdAndStockCode(@Param("userId") Long userId, @Param("newCost") Float newCost, @Param("newShares") Float newShares, @Param("stockCode") String stockCode);

    @Query(value = "SELECT * FROM user_stocks WHERE user_id = :userId", nativeQuery = true)
    List<UserStocks> getUserStockByUserId(@Param("userId") Long userId);
}
