package com.vanshProject.journalApp.controllers;

import com.vanshProject.journalApp.apiResponse.WeatherResponse;
import com.vanshProject.journalApp.entities.JournalEntry;
import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.services.UserService;
import com.vanshProject.journalApp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    WeatherService weatherService;
    @DeleteMapping
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getWeather(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        WeatherResponse weatherResponse = weatherService.getWeather("Meerut");
        String greetings = "";
        if(weatherResponse != null){
            greetings = " weather feels like " + weatherResponse.getCurrent().getTempC();
        }


        return new ResponseEntity<>( "Hi " + userName + greetings, HttpStatus.OK);
    }
}
