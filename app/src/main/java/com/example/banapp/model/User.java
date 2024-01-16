package com.example.banapp.model;

import java.time.LocalDateTime;

public class User {
    /*
     * プロパティ
     * テーブル設計 -> https://docs.google.com/spreadsheets/d/1PrJFGWgY5TMPXYbpVpp0eG9Kvhy2Fi4D32eF9IfSOig/edit#gid=0
     */
    private int id;
    private String name;
    private int cigarettePrice;
    private int cigarettePerDay;
    private int coin;
    private LocalDateTime createdAt;

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

    public int getCigarettePrice() {
        return cigarettePrice;
    }

    public void setCigarettePrice(int cigarettePrice) {
        this.cigarettePrice = cigarettePrice;
    }

    public int getCigarettePerDay() {
        return cigarettePerDay;
    }

    public void setCigarettePerDay(int cigarettePerDay) {
        this.cigarettePerDay = cigarettePerDay;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
