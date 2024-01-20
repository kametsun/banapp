package com.example.banapp.repository;

import com.example.banapp.model.History;
import com.example.banapp.model.Pet;
import com.example.banapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HistoryRepository {
    private static final String BASE_URL = "http://ec2-57-181-83-131.ap-northeast-1.compute.amazonaws.com:8000/";

    public static void createHistory(User user, Pet pet, CreateHistoryListener listener) {
        new Thread(() -> {
            try {
                // History オブジェクト作成
                History history = new History(
                        user.getId(),
                        pet.getId(),
                        History.calculateSavings(user, pet)
                );

                // JSONオブジェクトの作成
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("user_id", history.getUserId());
                jsonObject.put("pet_id", history.getPetId());
                jsonObject.put("more_money", history.getMoreMoney());

                URL url = new URL(BASE_URL + "histories/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);

                try (OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                    outputStream.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    if (listener != null) {
                        listener.createHistoryListener();
                    }
                }

                connection.disconnect();
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public interface CreateHistoryListener {
        void createHistoryListener();
    }
}
