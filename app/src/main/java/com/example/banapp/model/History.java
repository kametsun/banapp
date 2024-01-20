package com.example.banapp.model;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.time.LocalDateTime;

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

    public History(int userId, int petId, int moreMoney) {
        this.userId = userId;
        this.petId = petId;
        this.moreMoney = moreMoney;
    }

    public static int calculateSavings(User user, Pet pet) {
        // 1日あたりのタバコ総額
        int dailyCost = user.getCigarettePerDay() * user.getCigarettePrice();
        long daysBetween = 0;

        // ペットが生まれてから現在までの日数
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Duration duration = Duration.between(pet.getCreatedAt(), LocalDateTime.now());
            daysBetween = duration.toDays();
        }

        return (int) dailyCost * (int) daysBetween;
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
