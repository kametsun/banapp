package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private ImageView catImageView;  //画像格納
    private boolean isCat1Visible = true;  //画像を切り替えるためのフラグ
    private Handler handler; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        //ショップ画面遷移
        findViewById(R.id.bt_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ホーム画面からショップ画面に遷移
                Intent intent = new Intent(HomeActivity.this, ShopActivity.class);
                startActivity(intent);
            }
        });

        //アチーブメント画面遷移
        findViewById(R.id.bt_achievement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //ホーム画面からアチーブメント画面に遷移
                Intent intent = new Intent(HomeActivity.this, AchievementsActivity.class);
                startActivity(intent);
            }
        });

//        //ヒストリー画面遷移
//        findViewById(R.id.bt_history).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                //ホーム画面からヒストリー画面に遷移
//                Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    //ペットの画像を切り替えるメソッド
    private void switchCatImage() {
        if (isCat1Visible) {
            //2枚目に変更
            catImageView.setImageResource(R.drawable.cat2);
        } else {
            //1枚目に変更
            catImageView.setImageResource(R.drawable.cat1);
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

}
