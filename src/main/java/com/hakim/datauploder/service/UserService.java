package com.hakim.datauploder.service;

import com.hakim.datauploder.model.User;
import com.hakim.datauploder.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Cacheable("user.getById")
    public User getUser(long id){
        return userRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Could not get user by id : "+id));
    }
    public List<User> getAllUser(){
        return userRepo.findAll();
    }
}
