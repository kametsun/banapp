package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        //ボタンを押した時にホーム画面へ遷移（ボタン名は仮の名前のため後で変える）
        findViewById(R.id.btBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AchievementsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        //仮の変数（実際にはDBから取得した値を格納する）
        //連続餌やり日数
        int continueDays=5;
        //総餌やり日数
        int totalCount=6;

        //餌やりをしてみようのボタン
        Button btAchievementsFeed1= findViewById(R.id.btAchievementsFeed1);

        //3日連続餌やりをしようのボタン
        Button btAchievementsFeedDay3= findViewById(R.id.btAchievementsFeedDay3);

        //5日連続餌やりをしようのボタン
        Button btAchievementsFeedDay5= findViewById(R.id.btAchievementsFeedDay5);

        //1週間餌やりをしようのボタン
        Button btAchievementsFeedWeek1= findViewById(R.id.btAchievementsFeedWeek1);


        //餌やりをしてみよう
        if(0<totalCount){
            btAchievementsFeed1.setBackgroundResource(R.color.pinkButton);//ボタンの色をピンク色にする
            btAchievementsFeed1.setEnabled(true);//ボタンを押せるようにする
        }
        //3日連続餌やりをしよう
        if(3<=continueDays){
            btAchievementsFeedDay3.setBackgroundResource(R.color.pinkButton);//ボタンの色をピンク色にする
            btAchievementsFeedDay3.setEnabled(true);//ボタンを押せるようにする
        }
        //5日連続餌やりをしよう
        if(5<=continueDays){
            btAchievementsFeedDay5.setBackgroundResource(R.color.pinkButton);//ボタンの色をピンク色にする
            btAchievementsFeedDay5.setEnabled(true);//ボタンを押せるようにする
        }

        //1週間餌やりをしよう
        if(7<=totalCount){
            btAchievementsFeedWeek1.setBackgroundResource(R.color.pinkButton);//ボタンの色をピンク色にする
            btAchievementsFeedWeek1.setEnabled(true);//ボタンを押せるようにする
        }

//        //ボタンを押した時にヒストリー画面へ遷移（ボタン名は仮の名前のため後で変える）
//        findViewById(R.id.bt_test_history).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AchievementActivity.this, HistoryActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
