package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {
    private TextView tvPetName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        //Yesボタン処理
        findViewById(R.id.bt_yes).setOnClickListener(view -> {
            Intent intent = new Intent(CheckActivity.this, HomeActivity.class);

            startActivity(intent);
        });

        //Noボタン処理
        findViewById(R.id.bt_no).setOnClickListener(view -> {
            Intent intent = new Intent(CheckActivity.this, HomeActivity.class);

            startActivity(intent);
        });
    }
}
