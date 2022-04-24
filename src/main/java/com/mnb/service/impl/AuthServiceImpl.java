package com.mnb.service.impl;

import com.mnb.entity.User;
import com.mnb.entity.exception.InvalidArgumentsException;
import com.mnb.entity.exception.InvalidUserCredentialsException;
import com.mnb.repository.UserRepository;
import com.mnb.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
