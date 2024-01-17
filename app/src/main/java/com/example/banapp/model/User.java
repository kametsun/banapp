package com.example.banapp.model;

import java.time.LocalDateTime;

public class User {
    /*
     * プロパティ
     * テーブル設計 -> https://docs.google.com/spreadsheets/d/1PrJFGWgY5TMPXYbpVpp0eG9Kvhy2Fi4D32eF9IfSOig/edit#gid=0
     */
    private int id;
    private String name;
    private int cigarette_price;
    private int cigarette_per_day;
    private int coin;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCigarette_price() {
        return cigarette_price;
    }

    public void setCigarette_price(int cigarette_price) {
        this.cigarette_price = cigarette_price;
    }

    public int getCigarette_per_day() {
        return cigarette_per_day;
    }

    public void setCigarette_per_day(int cigarette_per_day) {
        this.cigarette_per_day = cigarette_per_day;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
