package com.vanshProject.journalApp;

import com.vanshProject.journalApp.repositories.UserRepoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userRepoImplTest {

    @Autowired
    UserRepoImpl userRepoImpl;

    @Test
    public void testSaveUser (){
        userRepoImpl.getUsersForSA();
    }

}
