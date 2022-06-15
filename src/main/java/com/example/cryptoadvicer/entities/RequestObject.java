package com.example.cryptoadvicer.entities;

public class RequestObject {

    private String cryptoName;
    private Long timestamp;

    public RequestObject() {
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
