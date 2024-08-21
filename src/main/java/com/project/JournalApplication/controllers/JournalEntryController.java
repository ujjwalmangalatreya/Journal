package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.JournalEntry;
import com.project.JournalApplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;


    @GetMapping("/getAllJournal")
    public List<JournalEntry> getAllJournals(){
       return journalEntryService.getAllEntry();
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id){
        return journalEntryService.getById(id).orElse(null);
    }

    @PostMapping("/createJournal")
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntryService.saveEntry(journalEntry);
        return true;
    }
    @DeleteMapping("/deleteJournal/{id}")
    public boolean removeJournalEntry(@PathVariable ObjectId id){
        journalEntryService.deleteById(id);
        return true;
    }
    @PutMapping("/updateJournal/{id}")
    public JournalEntry editJournalEntry(@PathVariable ObjectId id,@RequestBody JournalEntry journalEntry){
        JournalEntry oldJournalEntry = journalEntryService.getById(id).orElse(null);
        if(oldJournalEntry !=null){
            oldJournalEntry.setTitle(journalEntry.getTitle() !=null && !journalEntry.getTitle().equals("")? journalEntry.getTitle() : oldJournalEntry.getTitle());
            oldJournalEntry.setContent(journalEntry.getContent() !=null && !journalEntry.getContent().equals("")? journalEntry.getContent() : oldJournalEntry.getContent());
        }
        journalEntryService.saveEntry(oldJournalEntry);
       return oldJournalEntry;
    }

}
