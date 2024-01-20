package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DeathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_death);

        //リトライボタン処理
        findViewById(R.id.bt_retry).setOnClickListener(view -> {
            //ペット死亡画面からアチーブメント画面に遷移
            Intent intent = new Intent(DeathActivity.this, PetRegistrationActivity.class);
            startActivity(intent);
        });
    }
}
