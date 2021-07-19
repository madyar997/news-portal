package com.epam.rd.newsportal.service;

import com.epam.rd.newsportal.entity.User;
import com.epam.rd.newsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
       return userRepository.findAll();
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public void deactivateUserById(Long id){
        userRepository.deactivateUserById(id);
    }

}
