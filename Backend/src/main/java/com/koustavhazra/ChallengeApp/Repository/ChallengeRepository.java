package com.koustavhazra.ChallengeApp.Repository;

import com.koustavhazra.ChallengeApp.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChallengeRepository extends JpaRepository<Challenge, UUID> {
    // custom methods
    List<Challenge> findByMonthIgnoreCase(String month);  // filter challenges based on month
}
