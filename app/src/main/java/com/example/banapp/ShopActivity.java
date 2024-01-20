package com.example.banapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.banapp.model.Item;

public class ShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // 商品ごとにボタンを作成
        LinearLayout buttonsLayout = findViewById(R.id.bt_products);

        Item.getItems(items -> {
            runOnUiThread(() -> {
                for (Item item : items) {
                    int imageResourceId = getImageResourceId(item.getName());
                    addButton(buttonsLayout, item.getName(), item.getEnergy(), item.getPrice(), imageResourceId);
                }
            });
        });

        //戻る処理
        findViewById(R.id.bt_Back_shop).setOnClickListener(view -> {
            Intent intent = new Intent(ShopActivity.this, HomeActivity.class);

            startActivity(intent);
        });
    }

    private int getImageResourceId(String name) {
        switch (name) {
            case "クロワッサン":
                return R.drawable.croissant;

            case "ハンバーガー":
                return R.drawable.hamburger;

            case "アイスクリーム":
                return R.drawable.icecream;

            case "ラーメン":
                return R.drawable.ramen;

            case "ケーキ":
                return R.drawable.cake;
        }
        return -1;
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
        //戻るボタンクリック時の処理
        button.setOnClickListener(v -> {

            //ショップ画面からホームへ遷移
            Intent intent = new Intent(ShopActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        // LinearLayoutにボタンを追加
        layout.addView(button);
    }
}
