package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.Pet;
import com.example.banapp.repository.PetRepository;

public class PetRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_registration);

        EditText etPetName = findViewById(R.id.etPetNameInput);
        Button btPetSave = findViewById(R.id.btPetSave);

        btPetSave.setOnClickListener(v -> {
            String name = etPetName.getText().toString();
            Pet newPet = new Pet(getUserId(), name);

            PetRepository.createPetIntoDB(newPet, pet -> {
                savePetId(pet.getId());

                Intent intent = new Intent(PetRegistrationActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            });
        });
    }

    // ペットのIDをローカルに保存
    private void savePetId(int petId) {
        SharedPreferences sharedPreferences = getSharedPreferences("petInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("petId", petId);
        editor.apply();
    }

    // ローカルに保存されているユーザIDを取得
    private int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }
}
