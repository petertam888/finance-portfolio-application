package com.peter.financeportfolio.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.peter.financeportfolio.dto.UserBriefPortfolioDTO;
import com.peter.financeportfolio.model.Transaction;
import com.peter.financeportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // Allow requests from all origins
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
    public UserBriefPortfolioDTO checkUserBriefPortfolio(@PathVariable Long userId) throws JsonProcessingException {


        UserBriefPortfolioDTO userInfo = userService.getUserBriefPortfolio((userId));


//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(userInfo);

        System.out.println("passed");
//        System.out.println(json);
        return userInfo;
    }

    @GetMapping("/{userId}/transaction_records")
    public List<Transaction> checkUserTransactionRecords(@PathVariable Long userId) throws JsonProcessingException {


        List<Transaction> userTransactionRecords = userService.getUserTransactionRecords((userId));



//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(userInfo);
        System.out.println(userTransactionRecords);
        System.out.println("passed");
//        System.out.println(json);
        return userTransactionRecords;
    }

}
