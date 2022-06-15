package com.example.cryptoadvicer.controllers;

import com.example.cryptoadvicer.entities.RequestObject;
import com.example.cryptoadvicer.entities.StatisticObject;
import com.example.cryptoadvicer.service.CryptoAdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CryptoAdvicerController {

    @Autowired
    CryptoAdviceService cryptoAdviceService;

    @PostMapping(value = "/addCrypto")
    @ResponseBody
    ResponseEntity<String> addCrypto(@RequestBody String cryptoName) {
        if (cryptoAdviceService.addCrypto(cryptoName)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("");
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("");
    }


    @PostMapping(value = "/getCryptoAnalyzeForDay")
    StatisticObject getCryptoAnalyzeForDay(@RequestBody RequestObject requestObject) {
        StatisticObject statisticObject = cryptoAdviceService.getCryptoAnalyzeForDay(requestObject.getCryptoName(), requestObject.getTimestamp());
        return statisticObject;
    }

    @PostMapping(value = "/getCryptosByMonth")
    List<StatisticObject> getCryptosByMonth() {
        List<StatisticObject> statistics = cryptoAdviceService.getCryptoAnalyzeForMonth();
        return statistics;
    }


}
