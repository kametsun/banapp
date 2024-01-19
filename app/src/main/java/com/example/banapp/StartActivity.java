package com.example.banapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //ボタンを押した時
//        findViewById(R.id.bt_test_start).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //インテントの追加（これが遷移用ロジックです）
//                Intent intent = new Intent(HistoryActivity.this, StartActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}