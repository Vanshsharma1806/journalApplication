package com.vanshProject.journalApp.services;

import com.vanshProject.journalApp.entities.JournalEntry;
import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.repositories.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    JournalEntryRepo journalEntryRepo;
    @Autowired
    UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllEntriesofUser(String userName){
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        journalEntryRepo.deleteById(id);
        userService.saveUser(user);
    }
    public void deleteAll(){
        journalEntryRepo.deleteAll();
    }

}
