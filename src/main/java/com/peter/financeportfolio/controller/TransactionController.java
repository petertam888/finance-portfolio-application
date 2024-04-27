package com.peter.financeportfolio.controller;

import com.peter.financeportfolio.service.StockService;
import com.peter.financeportfolio.dto.FetchedStockInfoDTO;
import com.peter.financeportfolio.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*") // Allow requests from all origins
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
                        @PathVariable Float shares) {

        FetchedStockInfoDTO stockInfo = stockService.fetchStockInformation((stockCode));

        Object stockCurrentPrice = stockInfo.getStockPrice();
        Float stockPrice = Float.parseFloat((stockCurrentPrice.toString()));

        Map<String, Integer> acknowledgement = transactionService.buyStocksWithCurrentPrice((userId), (stockCode),
                (stockPrice), (shares));

        if (acknowledgement.get("statusCode") == 200){
            return 200;
        }


        return 500;
    }

    @GetMapping("/{userId}/record/{year}_{month}_{day}/{stockCode}/{stockPrice}/{shares}")
    public int addTransactionReord(@PathVariable Long userId,
                                   @PathVariable Integer year,
                                   @PathVariable Integer month,
                                   @PathVariable Integer day,
                                   @PathVariable String stockCode,
                                   @PathVariable Float stockPrice,
                                   @PathVariable Float shares) {

        LocalDate date = LocalDate.of(year, month, day);

        Map<String, Integer> acknowledgement = transactionService.addTransactionRecord((date), (userId), (stockCode), (stockPrice), (shares));

        if (acknowledgement.get("statusCode") == 200){
            return 200;
        }


        return 500;
    }



//    @GetMapping("/buyStock/{stockSymbol}/{numberOfStock}")
//    public ResponseEntity<Map<String, String>> buyStock(@PathVariable String stockSymbol, @PathVariable Integer numberOfStock){
//        Map<String, String> currentStockInfo = stockService.buyStock((stockSymbol),(numberOfStock));
//    }
}


