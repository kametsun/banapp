package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.User;
import com.example.banapp.repository.UserRepository;

public class UserInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        UserRepository.getUserById(getUserId(),user -> {


            //ユーザ名のTextView
            TextView tvUserNameDisplay= findViewById(R.id.tvUserNameDisplay);
            tvUserNameDisplay.setText(user.getName());

            //たばこの値段のTextView
            TextView tvUserPriceDisplay= findViewById(R.id.tvUserPriceDisplay);
            tvUserPriceDisplay.setText(String.valueOf(user.getCigarettePrice()));

            //たばこの本数のTextView
            TextView tvUserNumDisplay= findViewById(R.id.tvUserNumDisplay);
            tvUserNumDisplay.setText(String.valueOf(user.getCigarettePerDay()));

        });


        //戻る処理
        findViewById(R.id.btBackFromAchievevement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInfoActivity.this, HomeActivity.class);

                startActivity(intent);
            }
        });



    }
    private int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }


}
