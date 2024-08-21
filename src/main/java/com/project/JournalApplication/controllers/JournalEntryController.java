package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.JournalEntry;
import com.project.JournalApplication.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAllJournal")
    public List<JournalEntry> getAllJournals(){
       return null;
    }

    @PostMapping("/createJournal")
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntryService.saveEntry(journalEntry);
        return true;
    }
    @DeleteMapping("/deleteJournal/{id}")
    public JournalEntry removeJournalEntry(@PathVariable Long id){
        return null;
    }
    @PutMapping("/updateJournal/{id}")
    public JournalEntry editJournalEntry(@PathVariable Long id,@RequestBody JournalEntry journalEntry){
       return null;
    }

}
