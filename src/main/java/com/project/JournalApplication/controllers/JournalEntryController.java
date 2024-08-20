package com.project.JournalApplication.controllers;

import com.project.JournalApplication.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping("/getAllJournal")
    public List<JournalEntry> getAllJournals(){
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping("/createJournal")
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry){
        journalEntryMap.put(journalEntry.getId(),journalEntry);
        return true;
    }
}
