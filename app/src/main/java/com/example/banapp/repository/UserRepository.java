package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;
import com.example.banapp.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UserRepository {
    public interface GetUerListener {
        void  getUserListener(User user);
    }

    // IdからUserオブジェクトを返す
    public static void getUserById(int id, GetUerListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL("http://10.0.2.2:8000/users/" + id);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.connect();   // 実行

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
                    Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
                    ArrayList<User> users = gson.fromJson(response.toString(), userListType);
                    User user = users.get(0);
                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getUserListener(user));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
