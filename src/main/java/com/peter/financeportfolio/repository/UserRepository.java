package com.peter.financeportfolio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.peter.financeportfolio.model.User;



public interface UserRepository extends JpaRepository<User, Long> {

}
