package com.vanshProject.journalApp.controllers;

import com.vanshProject.journalApp.entities.JournalEntry;
import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user){
        if(user.getUserName() != null && user.getPassword() != null){
            try {
                userService.saveUser(user);
                return new ResponseEntity<>(user, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User>ls =  userService.getAllUsers();
        if(ls!= null && !ls.isEmpty()){
            return new ResponseEntity<>(ls,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveUser(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
