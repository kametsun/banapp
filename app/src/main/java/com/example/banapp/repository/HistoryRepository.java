package com.example.banapp.repository;

import com.example.banapp.model.History;
import com.example.banapp.model.Pet;
import com.example.banapp.model.User;

import java.util.concurrent.atomic.AtomicReference;

public class HistoryRepository {
    private static final String BASE_URL = "http://10.0.2.2:8000/";

    public static void getHistoryListener(int userId, int petId, GetHistoryListener listener) {
        AtomicReference<User> user = new AtomicReference<>();
        AtomicReference<Pet> pet = new AtomicReference<>();
        UserRepository.getUserById(userId, getedUser -> {
            user.set(getedUser);
        });
        PetRepository.getPetById(petId, getedPet -> {
            pet.set(getedPet);
        });
    }

    public interface GetHistoryListener {
        void getHistoryListener(History history);
    }
}
