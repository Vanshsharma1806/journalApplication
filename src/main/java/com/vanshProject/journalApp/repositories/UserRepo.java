package com.vanshProject.journalApp.repositories;

import com.vanshProject.journalApp.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
}
