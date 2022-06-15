package com.example.cryptoadvicer.entities;

public class PriceObject {
    private Long timestamp;

    private String symbol;

    private Double price;

    public PriceObject(String[] csvLine) {
        this.timestamp = Long.parseLong(csvLine[0]);
        this.symbol = csvLine[1];
        this.price = Double.parseDouble(csvLine[2]);
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "PriceObject{" +
                "timestamp=" + timestamp +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
