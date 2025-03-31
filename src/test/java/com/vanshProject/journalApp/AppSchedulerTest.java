package com.vanshProject.journalApp;

import com.vanshProject.journalApp.Cache.AppCache;
import com.vanshProject.journalApp.scheduler.AppScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppSchedulerTest {

    @Autowired
    AppScheduler appScheduler;



    @Test
    public void fetchUsersAndSendMail(){
        appScheduler.fetchUsersAndSendMail();
    }

}
