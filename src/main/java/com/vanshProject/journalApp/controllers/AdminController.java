package com.vanshProject.journalApp.controllers;

import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> ls = userService.getAllUsers();
        if(!ls.isEmpty()){
            return new ResponseEntity<>(ls, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User admin){
        userService.saveNewAdmin(admin);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }

}
