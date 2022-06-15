package com.example.cryptoadvicer.entities;

import java.io.Serializable;

public class StatisticObject implements Serializable {

    private String crypto;
    private double min;
    private double max;

    private double oldestPrice;
    private long oldestTimestamp;

    private double newestPrice;
    private long newestTimestamp;

    private double normalize;

    public StatisticObject() {
    }


    public StatisticObject(String crypto, double min, double max, double oldestPrice, long oldestTimestamp, double newestPrice, long newestTimestamp, double normalize) {
        this.crypto = crypto;
        this.min = min;
        this.max = max;
        this.oldestPrice = oldestPrice;
        this.oldestTimestamp = oldestTimestamp;
        this.newestPrice = newestPrice;
        this.newestTimestamp = newestTimestamp;
        this.normalize = normalize;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getOldestPrice() {
        return oldestPrice;
    }

    public void setOldestPrice(double oldestPrice) {
        this.oldestPrice = oldestPrice;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public double getNewestPrice() {
        return newestPrice;
    }

    public void setNewestPrice(double newestPrice) {
        this.newestPrice = newestPrice;
    }

    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

    public double getNormalize() {
        return normalize;
    }

    public void setNormalize(double normalize) {
        this.normalize = normalize;
    }

    @Override
    public String toString() {
        return "StatisticObject{" +
                "crypto='" + crypto + '\'' +
                ", min=" + min +
                ", max=" + max +
                ", oldestPrice=" + oldestPrice +
                ", oldestTimestamp=" + oldestTimestamp +
                ", newestPrice=" + newestPrice +
                ", newestTimestamp=" + newestTimestamp +
                ", normalize=" + normalize +
                '}';
    }
}
