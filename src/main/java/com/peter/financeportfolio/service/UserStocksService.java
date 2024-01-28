package com.peter.financeportfolio.service;
import com.peter.financeportfolio.model.UserStocks;
import com.peter.financeportfolio.repository.UserStocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserStocksService {

    private final UserStocksRepository userStocksRepository;

    @Autowired
    public UserStocksService(UserStocksRepository userStocksRepository) {
        this.userStocksRepository = userStocksRepository;
    }

    public List<UserStocks> getAllUserStocks(){
        return userStocksRepository.getAllUserStocks();
    }




}
