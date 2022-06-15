package com.example.cryptoadvicer.service;

import com.example.cryptoadvicer.entities.StatisticObject;
import com.example.cryptoadvicer.repositories.CryptoAdviceRepositorieImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoAdviceService {


    @Autowired
    private CryptoAdviceRepositorieImpl cryptoAdviceRepositorie;

    public boolean addCrypto(String cryptoName) {
        return cryptoAdviceRepositorie.addCrypto(cryptoName);
    }

    public StatisticObject getCryptoAnalyzeForDay(String cryptoName, long timestamp) {
        return cryptoAdviceRepositorie.getCryptoAnalyzeForDay(cryptoName, timestamp);
    }

    public List<StatisticObject> getCryptoAnalyzeForMonth() {
        return cryptoAdviceRepositorie.getCryptoAnalyzeForMonth(1641081600000L);
    }

}
