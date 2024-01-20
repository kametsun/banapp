package com.example.banapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // 商品一覧のデータ
        final String[] products = {"クロワッサン", "ラーメン", "アイスクリーム", "ハンバーガー", "ケーキ"};
        final int[] energies = {30, 80, 40, 60, 100};
        final int[] prices = {30, 80, 40, 60, 100};

        // 商品ごとにボタンを作成
        LinearLayout buttonsLayout = findViewById(R.id.bt_products);
        for (int i = 0; i < products.length; i++) {
            int imageResourceId = R.drawable.hatena;
            if (i == 0) {
                //クロワッサン画像
                imageResourceId = R.drawable.croissant;
            } else if (i == 1) {
                //ラーメン画像
                imageResourceId = R.drawable.ramen;
            } else if (i == 2) {
                //アイス画像
                imageResourceId = R.drawable.icecream;
            } else if (i == 3) {
                //ハンバーガー画像
                imageResourceId = R.drawable.hamburger;
            } else if (i == 4) {
                //ケーキ画像
                imageResourceId = R.drawable.cake;
            }

            addButton(buttonsLayout, products[i], energies[i], prices[i], imageResourceId);
        }

        //戻る処理
        findViewById(R.id.bt_Back_shop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });
    }

    private void addButton(LinearLayout layout, final String product, final int energy, final int price, int imageResourceId) {
        // ボタンを生成
        AppCompatButton button = new AppCompatButton(this);
        button.setText("商品名: " + product + "\nエネルギー: " + energy + "\n金額: " + price + "円");

        // 画像の大きさを調整
        Drawable drawable = getResources().getDrawable(imageResourceId);
        int imageSize = getResources().getDimensionPixelSize(R.dimen.image_size); // dimen リソースを使用してサイズを指定

        drawable.setBounds(64, 0, imageSize, imageSize);
        button.setCompoundDrawables(drawable, null, null, null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // マージンの設定
        params.setMargins(0, 0, 0, 32);

        // ボタンにレイアウトパラメータを設定
        button.setLayoutParams(params);

        //ボタンの色設定
        button.setBackgroundResource(R.drawable.button);

        // ボタンクリック時の処理
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            //戻るボタンクリック時の処理
            public void onClick(View v) {

                //ショップ画面からホームへ遷移
                Intent intent = new Intent(ShopActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // LinearLayoutにボタンを追加
        layout.addView(button);
    }
}
