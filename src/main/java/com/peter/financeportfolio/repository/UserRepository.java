package com.peter.financeportfolio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.peter.financeportfolio.model.User;
import org.springframework.data.jpa.repository.Query;
import com.peter.financeportfolio.model.UserDeposit;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDeposit, Long> {
    @Query(value = "SELECT user_id, deposit FROM user_deposit WHERE user_id = :userId", nativeQuery = true)
    Optional<UserDeposit> getUserDepositByUserId(@Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_deposit SET deposit = :newDeposit WHERE user_id = :userId", nativeQuery = true)
    void updateUserDepositByUserId(@Param("userId") Long userId, @Param("newDeposit") Float newDeposit);


}
