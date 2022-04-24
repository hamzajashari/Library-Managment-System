package com.mnb.service;

import com.mnb.entity.Role;
import com.mnb.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role, String gender, LocalDateTime dateOfBirth);
}
