package com.project.JournalApplication.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "journal_entries")
@Data
/*I can use @Getters @Setters as well, @Data Has the full collection of it so one annotation is enough*/
public class JournalEntry {

    @Id
    private ObjectId id;
    private String title;
    private String content;
}
