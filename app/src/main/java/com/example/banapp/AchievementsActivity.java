package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

//        //ボタンを押した時にホーム画面へ遷移（ボタン名は仮の名前のため後で変える）
//        findViewById(R.id.bt_test_home).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AchivementActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });
//        //ボタンを押した時にヒストリー画面へ遷移（ボタン名は仮の名前のため後で変える）
//        findViewById(R.id.bt_test_history).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AchivementActivity.this, HistoryActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}