package com.vanshProject.journalApp.controllers;

import com.vanshProject.journalApp.entities.JournalEntry;
import com.vanshProject.journalApp.services.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping
    public List<JournalEntry> getAllEntries(){
        return journalEntryService.getAll();
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getEntryById(@PathVariable ObjectId myId){
        return journalEntryService.getById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }
    @DeleteMapping
    public boolean deleteAlEntries(){
        journalEntryService.deleteAll();
        return true;
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry oldEntry = journalEntryService.getById(myId).orElse(null);
        if(oldEntry != null){
            String newContent = newEntry.getContent();
            oldEntry.setContent(newContent != null && !newContent.equals("")? newContent : oldEntry.getContent());
            String newTitle = newEntry.getTitle();
            oldEntry.setTitle(newTitle != null && !newTitle.equals("") ? newTitle : oldEntry.getTitle());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }
}
