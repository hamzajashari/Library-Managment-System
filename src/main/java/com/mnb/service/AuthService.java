package com.mnb.service;

import com.mnb.entity.User;

import java.time.LocalDate;

public interface AuthService {
    void update(String name,String oldUsername, String username, String password,String updatePassword, String dateOfBirth);
    void save(User user);
}
