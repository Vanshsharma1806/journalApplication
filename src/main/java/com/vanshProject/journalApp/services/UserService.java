package com.vanshProject.journalApp.services;

import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void saveUser(User user){
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
