package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.JournalEntry;
import com.project.JournalApplication.entity.User;
import com.project.JournalApplication.service.JournalEntryService;
import com.project.JournalApplication.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;


    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournals(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<JournalEntry> allData = user.getJournalEntries();
        if(allData !=null && !allData.isEmpty()){
            return new ResponseEntity<>(allData, HttpStatus.OK);
        }
       return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId id){
       Optional<JournalEntry> journalEntry = journalEntryService.getById(id);
       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry,@PathVariable String userName){
        try {
            journalEntryService.saveEntry(journalEntry,userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{userName}/{id}")
    public ResponseEntity<?> removeJournalEntry(@PathVariable ObjectId id,@PathVariable String userName){
        journalEntryService.deleteById(id,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{userName}/{id}")
    public ResponseEntity<?> editJournalEntry(@PathVariable ObjectId id,
                                              @RequestBody JournalEntry journalEntry,
                                              @PathVariable String userName
    ){
        JournalEntry oldJournalEntry = journalEntryService.getById(id).orElse(null);
        if(oldJournalEntry !=null){
            oldJournalEntry.setTitle(journalEntry.getTitle() !=null && !journalEntry.getTitle().equals("")? journalEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(journalEntry.getContent() !=null && !journalEntry.getContent().equals("")? journalEntry.getContent() : oldJournalEntry.getContent());
            journalEntryService.saveEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
