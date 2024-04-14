package com.peter.financeportfolio.service;

import com.peter.financeportfolio.model.UserDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import com.peter.financeportfolio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Integer> addCashAmount(Long userId, Float cashAmount){


        Optional<UserDeposit> userDepositOptional = userRepository.getUserDepositByUserId(userId);

        if (userDepositOptional.isPresent()) {
            UserDeposit userDeposit = userDepositOptional.get();
            Float userLatestDeposit =  cashAmount + userDeposit.getDeposit();
            userRepository.updateUserDepositByUserId(userId, userLatestDeposit);
        } else {
            UserDeposit userDeposit = new UserDeposit();
            userDeposit.setUser_id(userId);
            userDeposit.setDeposit(cashAmount);
            userRepository.save(userDeposit);
        }

        Map<String, Integer> acknowledgement = new HashMap<>();

        acknowledgement.put("statusCode", 200);


        return acknowledgement;
    }
}
