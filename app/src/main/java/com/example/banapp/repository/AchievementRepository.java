package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.banapp.model.Achievement;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class AchievementRepository {
    private static final String BASE_URL = "http://10.0.2.2:8000/";

    public static void getAllAchievement(GetAllAchievementsListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL + "achievements/");
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
                    Type listType = new TypeToken<List<Achievement>>() {
                    }.getType();
                    List<Achievement> achievements = gson.fromJson(response.toString(), listType);

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getAllAchievementListener(achievements));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public interface GetAllAchievementsListener {
        void getAllAchievementListener(List<Achievement> achievements);
    }
}
