package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        //ボタンを押した時にホーム画面へ遷移（ボタン名は仮の名前のため後で変える）
        findViewById(R.id.btBackFromAchievevement).setOnClickListener(new View.OnClickListener() {
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
        btAchievementsFeed1.setEnabled(false);
        //3日連続餌やりをしようのボタン
        Button btAchievementsFeedDay3= findViewById(R.id.btAchievementsFeedDay3);
        btAchievementsFeedDay3.setEnabled(false);
        //5日連続餌やりをしようのボタン
        Button btAchievementsFeedDay5= findViewById(R.id.btAchievementsFeedDay5);
        btAchievementsFeedDay5.setEnabled(false);
        //1週間餌やりをしようのボタン
        Button btAchievementsFeedWeek1= findViewById(R.id.btAchievementsFeedWeek1);
        btAchievementsFeedWeek1.setEnabled(false);

        //餌やりをしてみよう
        if(0<totalCount){
            btAchievementsFeed1.setText(R.string.bt_achievements_get);
            btAchievementsFeed1.setBackgroundColor(ContextCompat.getColor(this, R.color.pinkButton));//ボタンの色をピンク色にする
            btAchievementsFeed1.setEnabled(true);//ボタンを押せるようにする
        }
        //3日連続餌やりをしよう
        if(3<=continueDays){
            btAchievementsFeedDay3.setText(R.string.bt_achievements_get);
            btAchievementsFeedDay3.setBackgroundColor(ContextCompat.getColor(this, R.color.pinkButton));//ボタンの色をピンク色にする
            btAchievementsFeedDay3.setEnabled(true);//ボタンを押せるようにする
        }
        //5日連続餌やりをしよう
        if(5<=continueDays){
            btAchievementsFeedDay5.setText(R.string.bt_achievements_get);
            btAchievementsFeedDay5.setBackgroundColor(ContextCompat.getColor(this, R.color.pinkButton));//ボタンの色をピンク色にする
            btAchievementsFeedDay5.setEnabled(true);//ボタンを押せるようにする
        }

        //1週間餌やりをしよう
        if(7<=totalCount){
            btAchievementsFeedWeek1.setText(R.string.bt_achievements_get);
            btAchievementsFeedWeek1.setBackgroundColor(ContextCompat.getColor(this, R.color.pinkButton));//ボタンの色をピンク色にする
            btAchievementsFeedWeek1.setEnabled(true);//ボタンを押せるようにする
        }

        //餌やりをしてみようボタン
        findViewById(R.id.btAchievementsFeed1).setOnClickListener(view -> {

            //受け取るを達成に変更
            btAchievementsFeed1.setText(R.string.bt_achievements_received);
            btAchievementsFeed1.setBackgroundColor(ContextCompat.getColor(this, R.color.grayButton));  //ボタンの色を灰色にする
            btAchievementsFeed1.setEnabled(false);//ボタンを押せないようにする
        });

        //3日連続餌やりをしよう
        findViewById(R.id.btAchievementsFeedDay3).setOnClickListener(view -> {

            //GETをreceivedに変更
            btAchievementsFeedDay3.setText(R.string.bt_achievements_received);
            btAchievementsFeedDay3.setBackgroundColor(ContextCompat.getColor(this, R.color.grayButton));//ボタンの色を灰色にする
            btAchievementsFeedDay3.setEnabled(false);//ボタンを押せないようにする
        });

        //5日連続餌やりをしよう
        findViewById(R.id.btAchievementsFeedDay5).setOnClickListener(view -> {

            //GETをreceivedに変更
            btAchievementsFeedDay5.setText(R.string.bt_achievements_received);
            btAchievementsFeedDay5.setBackgroundColor(ContextCompat.getColor(this, R.color.grayButton));//ボタンの色を灰色にする
            btAchievementsFeedDay5.setEnabled(false);//ボタンを押せないようにする
        });

        //1週間餌やりをしよう
        findViewById(R.id.btAchievementsFeedWeek1).setOnClickListener(view -> {

            //GETをreceivedに変更
            btAchievementsFeedWeek1.setText(R.string.bt_achievements_received);
            btAchievementsFeedWeek1.setBackgroundColor(ContextCompat.getColor(this, R.color.grayButton));//ボタンの色を灰色にする
            btAchievementsFeedWeek1.setEnabled(false);//ボタンを押せないようにする
        });

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
