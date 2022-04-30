package com.mnb.service.impl;

import com.mnb.entity.Book;
import com.mnb.entity.Role;
import com.mnb.entity.User;
import com.mnb.entity.exception.InvalidArgumentsException;
import com.mnb.entity.exception.InvalidUpdateException;
import com.mnb.entity.exception.InvalidUserCredentialsException;
import com.mnb.repository.UserRepository;
import com.mnb.service.AuthService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void update(String name,String oldUsername, String password,String updatePassword,String dateOfBirth) throws InvalidUpdateException {
        User user=null;
        if (password==null || password.isEmpty() ||
                name==null || name.isEmpty() || dateOfBirth==null || dateOfBirth.equals("")) {
            throw new InvalidArgumentsException();
        }
        if(this.userRepository.findByUsername(oldUsername).isPresent()){
           //user= this.userRepository.findByUsernameAndPassword(oldUsername,password).orElseThrow(()-> new UsernameNotFoundException(username));
            user = this.userRepository.findByUsername(oldUsername).orElseThrow(()-> new UsernameNotFoundException(oldUsername));
            user.setName(name);
            user.setPassword(passwordEncoder.encode(updatePassword));
            user.setDateOfBirth(LocalDate.parse(dateOfBirth));
            //user.setId(this.userRepository.findByUsername(o).get().getId());
            this.userRepository.save(user);
        }
    }

    @Override
    public void save(User user) {
        this.userRepository.save(user);
    }

}
