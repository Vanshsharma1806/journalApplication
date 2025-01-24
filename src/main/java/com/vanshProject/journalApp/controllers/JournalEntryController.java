package com.vanshProject.journalApp.controllers;

import com.vanshProject.journalApp.entities.JournalEntry;
import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.services.JournalEntryService;
import com.vanshProject.journalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;
    @Autowired
    UserService userService;
    @PostMapping("/{userName}")
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
        if(myEntry.getTitle() != null && myEntry.getContent() != null){
            try {
                myEntry.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(myEntry,userName);
                return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
            }catch (Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllEntriesofUser(@PathVariable String userName){
        List<JournalEntry> all = journalEntryService.getAllEntriesofUser(userName);
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable ObjectId myId){
         Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);
         if(journalEntry.isPresent()){
             return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteEntryById(@PathVariable ObjectId myId, @PathVariable String userName){
        journalEntryService.deleteById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAlEntries(){
        journalEntryService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry, @PathVariable String userName){
        JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
        if(oldEntry != null){
            String newContent = newEntry.getContent();
            oldEntry.setContent(newContent != null && !newContent.equals("")? newContent : oldEntry.getContent());
            String newTitle = newEntry.getTitle();
            oldEntry.setTitle(newTitle != null && !newTitle.equals("") ? newTitle : oldEntry.getTitle());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
