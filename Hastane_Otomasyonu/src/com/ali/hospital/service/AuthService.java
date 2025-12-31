package com.ali.hospital.service;

import com.ali.hospital.domain.BaseUser;
import com.ali.hospital.repo.InMemoryStore;

import java.util.Optional;

public class AuthService {
    private final InMemoryStore store;

    public AuthService(InMemoryStore store) {
        this.store = store;
    }

    public Optional<BaseUser> login(String username, String password) {
        return store.authByUsernamePass(username, password);
    }
}
