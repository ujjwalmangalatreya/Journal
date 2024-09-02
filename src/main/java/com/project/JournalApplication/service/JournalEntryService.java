package com.project.JournalApplication.service;

import com.project.JournalApplication.entity.JournalEntry;
import com.project.JournalApplication.entity.User;
import com.project.JournalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            JournalEntry savedJournalEntries = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedJournalEntries);
            userService.saveUser(user);
        }catch (Exception err){
            System.out.println(err);
            throw new RuntimeException("An Error occurred when saving the entry ");
        }

    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllEntry(){
       return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed =false;
        try {
            User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception err) {
            throw new RuntimeException("An error occurred while deleting the entry"+err);
        }
        return removed;
    }

//    public List<JournalEntry> findByUserName(String userName){
//        return
//    }

}
