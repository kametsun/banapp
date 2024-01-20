package com.example.banapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

        private History history;

        private Pet pet;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_history);

//
//            // ユーザの取得
//            UserRepository.getUserById(getUserId(), getedUser -> {
//                user = getedUser;
//                if (user != null) {
//                    tvCoin.setText(String.valueOf(user.getCoin()));
//                }
//            });
//
//            // ペット取得
//            PetRepository.getPetById(getPetId(), getedPet -> {
//                pet = getedPet;
//                if (pet != null) {
//                    tvPetName.setText(pet.getName());   // ペットの名前を表示
//                }
//            });

            final String[] pets={"たろう","じろう","さぶろう","たろうまる","たろうたろう","あいうえお"};
            final int[] days={1,2,3,4,5,6};
            final int[] money={100,200,300,400,500,600};


            // ペットごとにTextViewを作成
            LinearLayout historyLayout = findViewById(R.id.linearLayoutHistory);

            for(int i=0; i<pets.length; i++) {
                addTextView(historyLayout,pets[i],days[i],money[i]);

            }

//            Item.getItems(histories -> {
//                runOnUiThread(() -> {
//                    for (History history : histories) {
//                        int imageResourceId = getImageResourceId(item.getName());
//                        addTextView(historyLayout, history);
//                    }
//                });
//            });

            //戻る処理
            findViewById(R.id.btBackFromHistory).setOnClickListener(view -> navigateToHome());
        }

        private void addTextView(LinearLayout layout,final String pet,final int day,final int money) {
            // TextViewを生成
            TextView textView = new TextView(this);
            textView.setText("ペットの名前: " + pet + "\n生存期間: " + day + "日\n得したお金: " + money + "円");


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            textView.setPadding(20,0,20,0);

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
