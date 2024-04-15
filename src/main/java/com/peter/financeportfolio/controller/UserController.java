package com.peter.financeportfolio.controller;

import com.peter.financeportfolio.dto.UserBriefPortfolioDTO;
import com.peter.financeportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{userId}/deposit/{cashAmount}")
    public int addDeposit(@PathVariable Long userId,
                                   @PathVariable Float cashAmount) {


        Map<String, Integer> acknowledgement = userService.addCashAmount((userId), (cashAmount));

        if (acknowledgement.get("statusCode") == 200){
            return 200;
        }


        return 500;
    }

    @GetMapping("/{userId}/portfolio")
    public UserBriefPortfolioDTO checkUserBriefPortfolio(@PathVariable Long userId) {


        UserBriefPortfolioDTO userInfo = userService.getUserBriefPortfolio((userId));



        return userInfo;
    }

}
