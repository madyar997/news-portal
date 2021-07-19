package com.epam.rd.newsportal.controller;


import com.epam.rd.newsportal.entity.User;
import com.epam.rd.newsportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    public final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id){
        userService.deactivateUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("users/deactivate/{id}")
    public ResponseEntity<?> deactivateUserById(@PathVariable("id") Long id){
        userService.deactivateUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
