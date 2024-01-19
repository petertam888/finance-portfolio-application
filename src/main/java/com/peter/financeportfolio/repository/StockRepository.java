package com.peter.financeportfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.peter.financeportfolio.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
}
