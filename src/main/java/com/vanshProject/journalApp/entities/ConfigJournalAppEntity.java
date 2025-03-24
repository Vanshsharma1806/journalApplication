package com.vanshProject.journalApp.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("config_journal_app")
@Data
public class ConfigJournalAppEntity {

    private String key;

    private String value;



}
