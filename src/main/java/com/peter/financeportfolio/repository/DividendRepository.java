package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserStocksDividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividendRepository extends JpaRepository<UserStocksDividend, Long> {
}
