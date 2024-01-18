package com.example.banapp.model;

import com.google.gson.annotations.SerializedName;

public class History {
    private int id;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("pet_id")
    private int petId;
    @SerializedName("more_money")
    private int moreMoney;

    public History(int id, int userId, int petId, int moreMoney) {
        this.id = id;
        this.userId = userId;
        this.petId = petId;
        this.moreMoney = moreMoney;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPetId() {
        return petId;
    }

    public int getMoreMoney() {
        return moreMoney;
    }
}
