package com.vanshProject.journalApp;

import com.vanshProject.journalApp.services.EmailServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailServices emailServices;

//    @Test
//    public void sendMailTest(){
//        emailServices.sendMail(
//                "vansh.sharma.cse.2021@miet.ac.in",
//                "sending mail using springboot",
//                "hi vansh how are you"
//        );
//
//    }

}
