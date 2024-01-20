package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.Pet;
import com.example.banapp.repository.PetRepository;

public class CheckActivity extends AppCompatActivity {
    private TextView tvPetName; // ペットの名前
    private Pet pet;

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

        //Yesボタン処理
        findViewById(R.id.bt_yes).setOnClickListener(view -> {
            Intent intent = new Intent(CheckActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        //Noボタン処理
        findViewById(R.id.bt_no).setOnClickListener(view -> {
            PetRepository.updateDeathAt(pet, () -> {
                resetPetId();
            });
            Intent intent = new Intent(CheckActivity.this, PetRegistrationActivity.class);
            startActivity(intent);
            finish();
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
}
