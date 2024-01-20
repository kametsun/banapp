package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.Pet;
import com.example.banapp.model.User;
import com.example.banapp.repository.HistoryRepository;
import com.example.banapp.repository.PetRepository;
import com.example.banapp.repository.UserRepository;

public class CheckActivity extends AppCompatActivity {
    private TextView tvPetName; // ペットの名前
    private Pet pet;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        tvPetName = findViewById(R.id.tv_petName);
        PetRepository.getPetById(getPetId(), getedPet -> {
            pet = getedPet;
            if (pet != null) {
                tvPetName.setText(pet.getName());
            }
        });

        UserRepository.getUserById(getUserId(), getedUser -> user = getedUser);

        //Yesボタン処理
        findViewById(R.id.bt_yes).setOnClickListener(view -> {
            Intent intent = new Intent(CheckActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        //Noボタン処理
        findViewById(R.id.bt_no).setOnClickListener(view -> {
            Log.d("42行目", "ボタンが押された");
            if ((user != null) && (pet != null)) {
                HistoryRepository.createHistory(user, pet, () -> {
                    Intent intent = new Intent(CheckActivity.this, DeathActivity.class);
                    startActivity(intent);
                });
            }
        });
    }

    // ローカルのペットIDをリセット
    private void resetPetId() {
        SharedPreferences sharedPreferences = getSharedPreferences("petInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("petId", -1);
        editor.apply();
    }

    private int getPetId() {
        SharedPreferences sharedPreferences = getSharedPreferences("petInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("petId", -1);
    }

    // ローカルのユーザIDを取得する
    private int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }
}
