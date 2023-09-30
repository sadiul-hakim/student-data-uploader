package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.User;
import com.hakim.datauploder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user){

        User savedUser = userService.save(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable long userId){

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){

        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
