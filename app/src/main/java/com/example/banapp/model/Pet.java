package com.example.banapp.model;

import com.google.gson.annotations.SerializedName;

/*
 * [
  {
    "id": 3,
    "user_id": 2,
    "name": "ペット3",
    "hunger": 40,
    "created_at": "2024-01-14T15:48:41",
    "death_at": null,
    "updated_at": "2024-01-14T15:48:41"
  }
]
 */

public class Pet {
    private int id;
    @SerializedName("user_id")
    private int userId;
    private String name;
    private int hunger;


    public Pet() {
    }

    public Pet(int id, int userId, String name, int hunger) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.hunger = hunger;
    }

    public Pet(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Pet(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
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
}
