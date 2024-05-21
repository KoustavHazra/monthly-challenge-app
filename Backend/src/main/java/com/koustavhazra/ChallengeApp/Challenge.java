package com.koustavhazra.ChallengeApp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity  // to helps to map the below fields to the db
public class Challenge {
    // every entity class must have at least one identifier (@Id)
    @Id
    private UUID id;

    // since month is a reserved word in database world,
    // so we are using this column annotation to keep the column name as "challengeMonth"
    // in the database
    @Column(name = "challengeMonth")
    private String month;
    private String description;

    // default constructor
    public Challenge() {}

    public Challenge(UUID id, String month, String description) {
        this.id = id;
        this.month = month;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
