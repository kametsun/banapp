package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.User;
import com.example.banapp.repository.UserRepository;

public class UserRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        // 部品オブジェクト生成
        EditText etUserName = findViewById(R.id.etUserNameInput);
        EditText etCigarettePrice = findViewById(R.id.etCigarettePriceInput);
        EditText etCigarettePerDay = findViewById(R.id.etCigarettePerDayInput);
        Button btSave = findViewById(R.id.btSave);

        // ボタンを押した時の処理
        btSave.setOnClickListener(v -> {
            String Name = etUserName.getText().toString();
            int price = Integer.parseInt(etCigarettePrice.getText().toString());
            int cigarettePerDay = Integer.parseInt(etCigarettePerDay.getText().toString());

            // オブジェクト作成
            User newUser = new User(Name, price, cigarettePerDay);

            UserRepository.createUserIntoDB(newUser, user -> {
                saveUserId(user.getId());
                UserRepository.updateUserCoins(user.getId(), 30, () -> {
                    Intent intent = new Intent(UserRegistrationActivity.this, PetRegistrationActivity.class);
                    startActivity(intent);
                });
            });
        });
    }

    /* SharedPreferencesクラスを利用してユーザのIDを保持する
     * https://developer.android.com/training/data-storage/shared-preferences?hl=ja
     * 基本的にここからユーザIDを取得してAPIなどを呼び起こす
     * SharePreferencesクラスの操作はActivityなどのContextを持つクラス内でしかできない
     */
    private void saveUserId(int userId) {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.apply();
    }
}
