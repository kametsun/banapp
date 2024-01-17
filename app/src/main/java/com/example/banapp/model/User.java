package com.example.banapp.model;

public class User {
    /*
     * モデルはモデルに関するロジックを実現したりするクラス。
     * API関連の処理はRepositoryクラスで実装
     * プロパティ
     * テーブル設計 -> https://docs.google.com/spreadsheets/d/1PrJFGWgY5TMPXYbpVpp0eG9Kvhy2Fi4D32eF9IfSOig/edit#gid=0
     */
    private int id;
    private String name;
    private int cigarettePrice;
    private int cigarettePerDay;
    private int coin;

    // デフォルトコンストラクタ -> 新規登録とかでプロパティを入れていってAPIに渡す
    public User() {
    }

    public User(int id, String name, int cigarettePrice, int cigarettePerDay) {
        this.id = id;
        this.name = name;
        this.cigarettePrice = cigarettePrice;
        this.cigarettePerDay = cigarettePerDay;
    }


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
}
