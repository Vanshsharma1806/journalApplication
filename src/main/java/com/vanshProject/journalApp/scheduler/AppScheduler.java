package com.vanshProject.journalApp.scheduler;

import com.vanshProject.journalApp.entities.JournalEntry;
import com.vanshProject.journalApp.entities.User;
import com.vanshProject.journalApp.enums.Sentiment;
import com.vanshProject.journalApp.repositories.UserRepoImpl;
import com.vanshProject.journalApp.services.EmailServices;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AppScheduler {

    private final UserRepoImpl userRepo;
    private final EmailServices emailServices;



    public AppScheduler(UserRepoImpl userRepo, EmailServices emailServices) {
        this.userRepo = userRepo;
        this.emailServices = emailServices;
    }


//    @Scheduled(cron = "0 * * * * *")
    public void fetchUsersAndSendMail(){
        List<User> usersForSA = userRepo.getUsersForSA();
        for (User user : usersForSA){
            List<JournalEntry> jounalEntries = user.getJournalEntries();
            List<Sentiment> sentiments = jounalEntries.stream()
                    .filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS)))
                    .map(x -> x.getSentiment())
                    .collect(Collectors.toList());
            Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
            for(Sentiment sentiment : sentiments){
                if(sentiment != null)
                sentimentCounts.put(sentiment,sentimentCounts.getOrDefault(sentiment,0)+ 1);
            }
            Sentiment mostFrequentSentiment = null;
            int maxFrequentSentimentCount = 0;
            for(Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()){
                if(entry.getValue() > maxFrequentSentimentCount){
                    maxFrequentSentimentCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment != null){
                emailServices.sendMail(user.getEmail(), "sentiment for last week", mostFrequentSentiment.toString());
            }
        }
    }



}
