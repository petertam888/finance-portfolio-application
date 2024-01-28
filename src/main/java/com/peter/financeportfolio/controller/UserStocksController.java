package com.peter.financeportfolio.controller;
import com.peter.financeportfolio.model.UserStocks;
import com.peter.financeportfolio.service.UserStocksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/stocks")
public class UserStocksController {
    private final UserStocksService userStocksService;


    @Autowired
    public UserStocksController(UserStocksService userStocksService) {

        this.userStocksService = userStocksService;
    }

    @GetMapping("/getAllStockInfo")
    public List<UserStocks> getAllUserStocks(){
        return userStocksService.getAllUserStocks();
    }

}


