package com.project.JournalApplication.controllers;

import ch.qos.logback.core.status.Status;
import com.project.JournalApplication.entity.JournalEntry;
import com.project.JournalApplication.service.JournalEntryService;
import jdk.jshell.Snippet;
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


    @GetMapping("/getAllJournal")
    public ResponseEntity<?> getAllJournals(){
        List<JournalEntry> allData = journalEntryService.getAllEntry();
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

    @PostMapping("/createJournal")
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry){
        try {
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/deleteJournal/{id}")
    public ResponseEntity<?> removeJournalEntry(@PathVariable ObjectId id){
        journalEntryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/updateJournal/{id}")
    public ResponseEntity<?> editJournalEntry(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
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
