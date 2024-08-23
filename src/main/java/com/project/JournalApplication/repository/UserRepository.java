package com.project.JournalApplication.repository;


import com.project.JournalApplication.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUsername(String username);
}
