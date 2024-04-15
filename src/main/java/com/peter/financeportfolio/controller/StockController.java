package com.peter.financeportfolio.controller;

import com.peter.financeportfolio.model.Stock;
import com.peter.financeportfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.peter.financeportfolio.dto.FetchedStockInfoDTO;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;


    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/addStockInfo/{stockSymbol}")
    public FetchedStockInfoDTO addStockInfo(@PathVariable String stockCode) {

        FetchedStockInfoDTO stockInfo = stockService.fetchStockInformation((stockCode));

        return stockInfo;
    }


//    @GetMapping("/buyStock/{stockSymbol}/{numberOfStock}")
//    public ResponseEntity<Map<String, String>> buyStock(@PathVariable String stockSymbol, @PathVariable Integer numberOfStock){
//        Map<String, String> currentStockInfo = stockService.buyStock((stockSymbol),(numberOfStock));
//    }
}


