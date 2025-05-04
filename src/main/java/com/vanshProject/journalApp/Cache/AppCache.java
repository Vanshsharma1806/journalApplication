package com.vanshProject.journalApp.Cache;

import com.vanshProject.journalApp.entities.ConfigJournalAppEntity;
import com.vanshProject.journalApp.repositories.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    @Autowired
    ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String > APP_CACHE;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigJournalAppEntity> ls = configJournalAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity: ls){
            APP_CACHE.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}