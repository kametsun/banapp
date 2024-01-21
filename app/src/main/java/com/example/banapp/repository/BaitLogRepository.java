package com.example.banapp.repository;

import android.os.Handler;
import android.os.Looper;

import com.example.banapp.model.User;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BaitLogRepository {
    private int user_id;
    @SerializedName("contunueday")
    private int contunueday;
    @SerializedName("totalcount")
    private int totalCount;
    @SerializedName("log_date")
    private String logDate;

    public BaitLogRepository(int user_id, int contunueday, int totalCount) {
        this.user_id = user_id;
        this.contunueday = contunueday;
        this.totalCount = totalCount;
    }

    public BaitLogRepository(int user_id, int contunueday, int totalCount, String logDate) {
        this.user_id = user_id;
        this.contunueday = contunueday;
        this.totalCount = totalCount;
        this.logDate = logDate;
    }

    public static void getBaitLog(User user, GetBaitLogListener listener) {
        new Thread(() -> {
            String baseUrl = "http://10.0.2.2:8000/users/baitlogs/";
            try {
                URL url = new URL(baseUrl + user.getId());
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
                    Type listType = new TypeToken<List<BaitLogRepository>>() {
                    }.getType();
                    List<BaitLogRepository> baitLogs = gson.fromJson(response.toString(), listType);
                    if (listener != null && !baitLogs.isEmpty()) {
                        new Handler(Looper.getMainLooper()).post(() -> listener.getBaitLogListener(baitLogs.get(0)));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    public int getUser_id() {
        return user_id;
    }

    public int getContunueday() {
        return contunueday;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public String getLogDate() {
        return logDate;
    }

    public interface GetBaitLogListener {
        void getBaitLogListener(BaitLogRepository baitLog);
    }
}
