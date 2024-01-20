package com.example.banapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banapp.model.Pet;
import com.example.banapp.model.User;
import com.example.banapp.repository.PetRepository;
import com.example.banapp.repository.UserRepository;

public class HomeActivity extends AppCompatActivity {
    private ImageView catImageView;  //画像格納
    private boolean isCat1Visible = true;  //画像を切り替えるためのフラグ
    private Pet pet;    // ペットオブジェクトを格納する
    private User user;  // ユーザオブジェクトを格納
    private TextView tvPetName, tvCoin; //ペットの名前を格納する, コイン数
    private Handler handler; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvPetName = findViewById(R.id.pet_name);
        tvCoin = findViewById(R.id.tv_coin);

        // ユーザを取得
        UserRepository.getUserById(getUserId(), getedUser -> {
            user = getedUser;
            if (user != null) {
                tvCoin.setText(String.valueOf(user.getCoin()));
            }
        });

        // ペット取得
        PetRepository.getPetById(getPetId(), getedPet -> {
            pet = getedPet;
            if (pet != null) {
                tvPetName.setText(pet.getName());   // ペットの名前を表示
            }
        });

        catImageView = findViewById(R.id.catImageView);

        // 初期画像を表示
        catImageView.setImageResource(R.drawable.cat1);

        handler = new Handler(Looper.getMainLooper());

        // 定期的に画像を切り替える
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switchCatImage();
                handler.postDelayed(this, 1000); // 1秒ごとに切り替える
            }
        }, 1000); // 初回は1秒後に実行

        //プロフィール画面遷移
        findViewById(R.id.bt_profile).setOnClickListener(view -> {

            //ホーム画面からショップ画面に遷移
            Intent intent = new Intent(HomeActivity.this, UserInfoActivity.class);
            startActivity(intent);
        });

        //ショップ画面遷移
        findViewById(R.id.bt_shop).setOnClickListener(view -> {

            //ホーム画面からショップ画面に遷移
            Intent intent = new Intent(HomeActivity.this, ShopActivity.class);
            startActivity(intent);
        });

        //アチーブメント画面遷移
        findViewById(R.id.bt_achievement).setOnClickListener(view -> {

            //ホーム画面からアチーブメント画面に遷移
            Intent intent = new Intent(HomeActivity.this, AchievementsActivity.class);
            startActivity(intent);
        });

        //ヒストリー画面遷移
        findViewById(R.id.bt_history).setOnClickListener(view -> {

            //ホーム画面からヒストリー画面に遷移
            Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
            startActivity(intent);
        });

    }

    //ペットの画像を切り替えるメソッド
    private void switchCatImage() {
        Random random = new Random();
        int randomNum = random.nextInt(10);
        if (isCat1Visible) {
            if(randomNum == 0){
                //4枚目に変更
                catImageView.setImageResource(R.drawable.cat2);
            }else {
                //2枚目に変更
                catImageView.setImageResource(R.drawable.cat2);
            }
        } else {
            if(randomNum == 0) {
                //3枚目に変更
                catImageView.setImageResource(R.drawable.cat1);
            }else {
                //1枚目に変更
                catImageView.setImageResource(R.drawable.cat1);
            }
        }
        //フラグを切り替え
        isCat1Visible = !isCat1Visible;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Handlerのメモリリークを防ぐためにActivityが破棄されたらHandlerも終了する
        handler.removeCallbacksAndMessages(null);
    }

    // ローカルのペットIDを取得する
    private int getPetId() {
        SharedPreferences sharedPreferences = getSharedPreferences("petInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("petId", -1);
    }

    // ローカルのペットIDを取得する
    private int getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        return sharedPreferences.getInt("userId", -1);
    }
}
