package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.banapp.model.History;
import com.example.banapp.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* [memo]
 *  AndroidエミュレータからPCのローカルホストを指定するときは 10.0.2.2 を使用する
 */

public class UserRepository {
    private static final String BASE_URL = "http://10.0.2.2:8000/";

    // IdからUserオブジェクトを返す
    public static void getUserById(int id, GetUerListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + "users/" + id);
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

    // ユーザを作成する

    public static void createUserIntoDB(User user, CreateUserListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + "users/");
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

    // コインを変更する

    public static void updateUserCoins(int userId, int coin, UpdateUserCoinsListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + "users/" + userId + "/coin");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PATCH");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);

                JSONObject jsonBody = new JSONObject();
                jsonBody.put("coin", coin);

                try (OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = jsonBody.toString().getBytes(StandardCharsets.UTF_8);
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

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.updateUserCoinsListener(userId, coin));
                    }
                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // ユーザのヒストリーを取得
    public static void getHistoriesByUserId(int userId, GetHistoriesListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + "users/" + userId + "/histories");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<History>>() {
                    }.getType();
                    List<History> histories = gson.fromJson(response.toString(), listType);

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getHistoriesListener(histories));
                    }
                }
            } catch (IOException e) {
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

    public interface UpdateUserCoinsListener {
        void updateUserCoinsListener(int userId, int coin);
    }

    public interface GetHistoriesListener {
        void getHistoriesListener(List<History> histories);
    }
}
