package com.vanshProject.journalApp.services;

import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepo userRepo;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveUser(User user){
        userRepo.save(user);
    }
    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User findByUserName(String userName){
        return userRepo.findByUserName(userName);
    }

    public void deleteUser(User user){
         userRepo.delete(user);
    }



}
