package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.banapp.model.Pet;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetRepository {
    // URL
    private static final String URL = "http://10.0.2.2:8000/";

    public static void updateDeathAt(Pet pet, UpdateDeathAtListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(URL + "pets/" + pet.getId() + "/death");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    Log.d("レスポンス200", "なぜ");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    Gson gson = new Gson();
                    Log.d("Gsonオブジェ", "どこ");
                    JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
                    Log.d("49ぎょうめ", "どこ");
                    String deathTimeStr = jsonResponse.get("death_time").getAsString();

                    DateTimeFormatter formatter = null;
                    LocalDateTime deathTime = null;

                    /*
                     * "death_time": "2024-01-19 22:10:53"
                     * */
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        deathTime = LocalDateTime.parse(deathTimeStr, formatter);
                    }
                    pet.setDeathTime(deathTime);
                    Log.d("60ぎょうめ ペットの時間 -> ", pet.getDeathTime().toString());

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.updateDeathAtListener());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

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

    public static void createPetIntoDB(Pet pet, CreatePetIntoDBListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(URL + "pets/");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);

                String jsonInput = new Gson().toJson(pet);

                try (OutputStream outputStream = connection.getOutputStream()) {
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
                        new Handler(Looper.getMainLooper()).post(() -> listener.createPetIntoDBListener(pet));
                    }
                }
            } catch (IOException | JSONException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // ペット作成エンドポイント

    public static void updatePetEnergy(int petId, int energy, UpdatePeteEnergyListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(URL + "pets/" + petId + "/hunger");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("PATCH");
                connection.setRequestProperty("Content-Type", "application/json; utf-8");
                connection.setDoOutput(true);

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("hunger", energy);

                try (OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
                    outputStream.write(input, 0, input.length);
                }

                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.updatePetEnergyListener(energy));
                    }
                }
            } catch (JSONException | IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public interface UpdateDeathAtListener {
        void updateDeathAtListener();
    }

    public interface CreatePetIntoDBListener {
        void createPetIntoDBListener(Pet pet);
    }

    public interface GetPetListener {
        void getPetListener(Pet pet);
    }

    public interface UpdatePeteEnergyListener {
        void updatePetEnergyListener(int energy);
    }
}
