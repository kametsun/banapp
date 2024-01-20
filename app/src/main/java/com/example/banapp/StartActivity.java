package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Handlerを使用して3秒後にMainActivityに遷移する
        new Handler().postDelayed(() -> {
            Intent intent;
            if ((getUserId() == -1) && (getPetId() == -1)) {
                intent = new Intent(StartActivity.this, UserRegistrationActivity.class);
            } else if ((getUserId() != -1) && (getPetId() == -1)) {
                intent = new Intent(StartActivity.this, PetRegistrationActivity.class);
            } else {
                intent = new Intent(StartActivity.this, CheckActivity.class);
            }
            startActivity(intent);
            finish();
        }, 3000); // 3秒
    }

    private int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }

    private int getPetId() {
        SharedPreferences sharedPreferences = getSharedPreferences("petInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("petId", -1);
    }
}
