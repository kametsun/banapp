package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.History;
import com.example.banapp.model.Pet;
import com.example.banapp.repository.PetRepository;
import com.example.banapp.repository.UserRepository;

public class HistoryActivity extends AppCompatActivity {

    private History history;

    private Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // ペットごとにTextViewを作成
        LinearLayout historyLayout = findViewById(R.id.linearLayoutHistory);

        // ユーザのヒストリー取得
        UserRepository.getHistoriesByUserId(getUserId(), histories -> {
            for (History history : histories) {
                PetRepository.getPetById(history.getPetId(), pet -> {
                    addTextView(historyLayout, pet.getName(), pet.daysFromBirthToDeath(), history.getMoreMoney());
                });
            }
        });

        //戻る処理
        findViewById(R.id.btBackFromHistory).setOnClickListener(view -> navigateToHome());
    }

    private void addTextView(LinearLayout layout, final String pet, final int day, final int money) {
        // TextViewを生成
        TextView textView = new TextView(this);
        textView.setText("ペットの名前: " + pet + "\n生存期間: " + day + "日\n得したお金: " + money + "円");


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textView.setPadding(20, 0, 20, 0);

//            // マージンの設定
        params.setMargins(0, 0, 0, 32);


        // ボタンにレイアウトパラメータを設定
        textView.setLayoutParams(params);

        //文字を中央揃えに設定
        textView.setGravity(Gravity.CENTER);

        //ボタンの色設定
        textView.setBackgroundResource(R.drawable.shape_style_white);

        // LinearLayoutにボタンを追加
        layout.addView(textView);
    }

    private void navigateToHome() {
        Intent intent = new Intent(HistoryActivity.this, HomeActivity.class);
        startActivity(intent);
    }


    private int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }
}
