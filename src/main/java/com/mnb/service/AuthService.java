package com.mnb.service;

import com.mnb.entity.User;

public interface AuthService {
    User login(String username, String password);
}
