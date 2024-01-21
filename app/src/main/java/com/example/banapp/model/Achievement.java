package com.example.banapp.model;

public class Achievement {
    private int id;
    private String name;
    private String description;
    private int reward_coin;

    public Achievement(int id, String name, String description, int reward_coin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reward_coin = reward_coin;
    }
}
