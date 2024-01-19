package com.peter.financeportfolio.controller;

import com.peter.financeportfolio.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;


    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/getStockInfo/{stockSymbol}")
    public ResponseEntity<Map<String, String>> getStockInfo(@PathVariable String stockSymbol) {
        Map<String, String> stockInfo = stockService.fetchStockInformation((stockSymbol));

        if (stockInfo.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(stockInfo);
    }
}


