package com.example.cryptoadvicer.entities;

import com.example.cryptoadvicer.utils.TimeConvertors;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AnalyzeObject {

    private String cryptoName;
    private TreeMap<Long, PriceObject> tree;

    public AnalyzeObject(String cryptoName) {
        this.cryptoName = cryptoName;
        this.tree = new TreeMap<>();
    }

    public void addValue(PriceObject priceObject) {
        tree.put(priceObject.getTimestamp(), priceObject);
    }


    public StatisticObject getNormalizedForMonth(Long timestamp) {
        Date date = new Date(timestamp);
        SortedMap<Long, PriceObject> sortedMap = this.tree.subMap(TimeConvertors.atEndOfMonth(date), TimeConvertors.atEndOfMonth(date));
        return getStatistic(sortedMap);
    }

    public StatisticObject getNormalizedForDay(Long timestamp) {
        Date date = new Date(timestamp);
        SortedMap<Long, PriceObject> sortedMap = this.tree.subMap(TimeConvertors.atStartOfDay(date), TimeConvertors.atEndOfDay(date));
        return getStatistic(sortedMap);
    }

    private StatisticObject getStatistic(SortedMap<Long, PriceObject> sortedMap) {

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        long newestTimestamp = Long.MIN_VALUE;
        long oldestTimestamp = Long.MAX_VALUE;

        double oldestPrice = 0.0;

        double newestPrice = 0.0;

        for (Map.Entry<Long, PriceObject> entry : sortedMap.entrySet()) {
            PriceObject priceObject = entry.getValue();

            double curPr = priceObject.getPrice();
            if (curPr < min) {
                min = curPr;
            }

            if (curPr > max) {
                max = curPr;
            }

            Long curTimestamp = priceObject.getTimestamp();
            if (curTimestamp > newestTimestamp) {
                newestPrice = curPr;
                newestTimestamp = curTimestamp;
            }

            if (curTimestamp < oldestTimestamp) {
                oldestTimestamp = curTimestamp;
                oldestPrice = curPr;
            }


        }

        double normalize = (max - min) / min;
        return new StatisticObject(this.cryptoName, min, max, oldestPrice, oldestTimestamp, newestPrice, newestTimestamp, normalize);


    }

}
