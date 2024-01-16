package com.example.banapp.repository;

import android.util.Log;

import com.example.banapp.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UserRepository {
    // IdからUserオブジェクトを返す
    public static User getUserById(int id) {
        try {
            URL url = new URL("http://localhost:8000/users/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();   //接続

            int responseCode = connection.getResponseCode();    // レスポンスコード確認
            Log.d(String.valueOf(responseCode), "エラー");
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                Gson gson = new Gson();
                return gson.fromJson(response.toString(), User.class);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
