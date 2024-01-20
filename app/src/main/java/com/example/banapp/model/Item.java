package com.example.banapp.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Item {
    private static final String BASE_URL = "http://ec2-57-181-83-131.ap-northeast-1.compute.amazonaws.com:8000/items/";
    private int id;
    private String name;
    private int price;
    private int energy;

    public Item(int id, String name, int price, int energy) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.energy = energy;
    }

    public static void getItems(GetItemsListener listener) {
        new Thread(() -> {
            try {
                URL url = new URL(BASE_URL);
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
                    Type listType = new TypeToken<List<Item>>() {
                    }.getType();
                    List<Item> items = gson.fromJson(response.toString(), listType);

                    if (listener != null) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getItemsListener(items));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public int getPrice() {
        return price;
    }

    public int getEnergy() {
        return energy;
    }

    public String getName() {
        return name;
    }

    public interface GetItemsListener {
        void getItemsListener(List<Item> items);
    }
}
