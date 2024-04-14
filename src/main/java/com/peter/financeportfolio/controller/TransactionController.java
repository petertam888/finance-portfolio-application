package com.peter.financeportfolio.controller;

import com.peter.financeportfolio.model.Stock;
import com.peter.financeportfolio.service.StockService;
import com.peter.financeportfolio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final StockService stockService;


    @Autowired
    public TransactionController(TransactionService transactionService, StockService stockService) {
        this.transactionService = transactionService;
        this.stockService = stockService;
    }

    @GetMapping("/{userId}/buy/{stockCode}/{shares}")
    public int buyStock(@PathVariable Long userId,
                        @PathVariable String stockCode,
                        @PathVariable Integer shares) {

        Map<String, Object> stockInfo = stockService.fetchStockInformation((stockCode));

        Object stockCurrentPrice = stockInfo.get("stockPrice");
        Float stockPrice = Float.parseFloat((stockCurrentPrice.toString()));

        Map<String, Integer> acknowledgement = transactionService.buyStocksWithCurrentPrice((userId), (stockCode),
                (stockPrice), (shares));

        if (acknowledgement.get("statusCode") == 200){
            return 200;
        }


        return 500;
    }

    @GetMapping("/getAllStockInfo")
    public ResponseEntity<List<Stock>> getAllStocks(){
        List<Stock> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

//    @GetMapping("/buyStock/{stockSymbol}/{numberOfStock}")
//    public ResponseEntity<Map<String, String>> buyStock(@PathVariable String stockSymbol, @PathVariable Integer numberOfStock){
//        Map<String, String> currentStockInfo = stockService.buyStock((stockSymbol),(numberOfStock));
//    }
}


