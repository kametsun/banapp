package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.banapp.model.Pet;
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

public class PetRepository {
    private static final String URL = "http://10.0.2.2:8000/";

    // IdからPetオブジェクトを返す
    public static void getPetById(int id, GetPetListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(URL + "pets/" + id);
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

                    // JSON配列を解析
                    JSONArray jsonArray = new JSONArray(response.toString());
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    Pet pet = new Pet(
                            jsonObject.getInt("id"),
                            jsonObject.getInt("user_id"),
                            jsonObject.getString("name"),
                            jsonObject.getInt("hunger")
                    );

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getPetListener(pet));
                    }
                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // ペット作成エンドポイント

    public static void createPetIntoDB(Pet pet, CreatePetIntoDBListener listener) {
        Log.d("ここはcratePetIntoDB", "助けて");
        new Thread(() -> {
            try {
                Log.d("ここはtryのなか", "助けて");
                URL url = new URL(URL + "pets/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                String jsonInput = new Gson().toJson(pet);

                try (OutputStream outputStream = connection.getOutputStream()) {
                    Log.d("ここはtry->try", "助けて");
                    byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
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
                    int petId = jsonObject.getInt("id");
                    pet.setId(petId);

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.createPetIntoDB(pet));
                    }
                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public interface CreatePetIntoDBListener {
        void createPetIntoDB(Pet pet);
    }

    public interface GetPetListener {
        void getPetListener(Pet pet);
    }
}
