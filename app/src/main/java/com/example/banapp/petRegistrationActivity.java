package com.example.banapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class petRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_registration);

        EditText etPetName = findViewById(R.id.etPetNameInput);
        Button btPetSave = findViewById(R.id.btPetSave);

        btPetSave.setOnClickListener(v -> {
            String name = etPetName.getText().toString();

        });
    }
}
