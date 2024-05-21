package com.koustavhazra.ChallengeApp.Service;

import com.koustavhazra.ChallengeApp.Challenge;
import com.koustavhazra.ChallengeApp.Exception.InvalidMonthException;
import com.koustavhazra.ChallengeApp.Exception.ResourceNotFoundException;
import com.koustavhazra.ChallengeApp.Repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class ChallengeService {
    // private final List<Challenge> challenges = new ArrayList<>();

    @Autowired
    ChallengeRepository challengeRepository;

    // service constructor
    public ChallengeService() {
    }

    // get all challenges
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    // get all challenges by month
    public List<Challenge> getAllChallengesByMonth(String month) {
        if (month == null || month.isEmpty()) {
            throw new InvalidMonthException("Month cannot be null or empty");
        }

        List<Challenge> challengesByMonth  = challengeRepository.findByMonthIgnoreCase(month);

        if (challengesByMonth.isEmpty()) {
            throw new ResourceNotFoundException("No challenges found for the month: " + month);
        }
        return challengesByMonth;
    }

    // get a single challenge
    public Challenge getChallenge(UUID uuid) {
        if (uuid == null || Objects.equals(uuid.toString(), "")) {
            throw new ResourceNotFoundException("UUID cannot be null or empty string");
        }

        return challengeRepository
                .findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Challenge not found with UUID: " + uuid));
    }

    // create a new challenge
    public boolean createNewChallenge(@RequestBody Challenge challenge) {
        if (challenge != null) {
            // automatically adding id to each post request
            challenge.setId(UUID.randomUUID());
            // adding each challenge from post request to list
            challengeRepository.save(challenge);
            return true;
        } else {
            return false;
        }
    }

    // update a challenge
    public boolean updateChallenge(@PathVariable UUID uuid, @RequestBody Challenge updatedChallenge) {
        if (uuid == null || Objects.equals(uuid.toString(), "")) {
            throw new ResourceNotFoundException("UUID cannot be null or empty string.");
        }

        return challengeRepository.findById(uuid)
                .map(challenge -> {
                    if (!Objects.equals(updatedChallenge.getMonth(), "")) {
                        challenge.setMonth(updatedChallenge.getMonth());
                    }
                    if (!Objects.equals(updatedChallenge.getDescription(), "")) {
                        challenge.setDescription(updatedChallenge.getDescription());
                    }
                    challengeRepository.save(challenge);
                    return true;
                }).orElse(false);
    }

    // delete a challenge
    public boolean deleteChallenge(@PathVariable UUID uuid) {
        if (uuid == null || Objects.equals(uuid.toString(), "")) {
            throw new ResourceNotFoundException("UUID cannot be null or empty string.");
        }
        return challengeRepository.findById(uuid)
                .map(challenge -> {
                    challengeRepository.delete(challenge);
                    return true;
                }).orElse(false);
    }

}
