package com.project.JournalApplication.service;

import com.project.JournalApplication.entity.JournalEntry;
import com.project.JournalApplication.entity.User;
import com.project.JournalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
            userService.saveEntry(user);
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

    public void deleteById(ObjectId id, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
         journalEntryRepository.deleteById(id);
    }

}
