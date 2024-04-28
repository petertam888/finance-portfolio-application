package com.peter.financeportfolio.controller;
import com.peter.financeportfolio.dto.UserProfitInfoDTO;
import com.peter.financeportfolio.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*") // Allow requests from all origins
@RequestMapping("/api/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;

    @Autowired
    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }
    @GetMapping("/{userId}/brief_profits")
    public UserProfitInfoDTO getUserProfits(@PathVariable Long userId) {


        UserProfitInfoDTO user_profits_info= analysisService.getAccountProfitsInfo(userId);


        return user_profits_info;
    }



}
