package com.peter.financeportfolio.repository;

import com.peter.financeportfolio.model.UserPortfolioYoYRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserPortfolioYoYRecordsRepository extends JpaRepository<UserPortfolioYoYRecords, Long> {
}
