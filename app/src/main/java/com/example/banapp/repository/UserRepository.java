package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.banapp.model.User;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/* [memo]
 *  AndroidエミュレータからPCのローカルホストを指定するときは 10.0.2.2 を使用する
 */

public class UserRepository {
    // IdからUserオブジェクトを返す
    public static void getUserById(int id, GetUerListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8000/users/" + id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.connect();   // 実行

                int responseCode = connection.getResponseCode();    //レスポンスコード確認
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // JSON配列を解析
                    JSONArray jsonArray = new JSONArray(response.toString());
                    // 配列の最初の要素を取得
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    User user = new User(
                            jsonObject.getInt("id"),
                            jsonObject.getString("name"),
                            jsonObject.getInt("cigarette_price"),
                            jsonObject.getInt("cigarette_per_day")
                    );

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getUserListener(user));
                    }
                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void createUserIntoDB(User user, CreateUserListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8000/users/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                String jsonInputString = new Gson().toJson(user);

                try (OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    outputStream.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JSONObject jsonObject = new JSONObject(response.toString());
                    int userId = jsonObject.getInt("id");
                    user.setId(userId);

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.createUserListener(user));
                    }

                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public interface GetUerListener {
        void getUserListener(User user);
    }

    public interface CreateUserListener {
        void createUserListener(User user);
    }
}
