package com.koustavhazra.ChallengeApp.Controller;

import com.koustavhazra.ChallengeApp.Challenge;
import com.koustavhazra.ChallengeApp.Service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000/")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/challenges")
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @GetMapping("/challenges/month/{month}")
    public ResponseEntity<List<Challenge>> getAllChallengesByMonth(@PathVariable String month) {
        List<Challenge> challengesByMonth = challengeService.getAllChallengesByMonth(month);
        return new ResponseEntity<>(challengesByMonth, HttpStatus.OK);
    }

    @GetMapping("/challenge/{uuid}")
    public ResponseEntity<Challenge> getChallenge(@PathVariable UUID uuid) {
        Challenge challenge = challengeService.getChallenge(uuid);
        return new ResponseEntity<>(challenge, HttpStatus.OK);
    }

    @PostMapping("/challenge")
    public ResponseEntity<String> addChallenge(@RequestBody Challenge challenge) {
        boolean isNewChallenge = challengeService.createNewChallenge(challenge);
        if (isNewChallenge) {
            return new ResponseEntity<>("Challenge added successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge not added successfully.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/challenge/{uuid}")
    public ResponseEntity<String> updateChallenge(@PathVariable UUID uuid, @RequestBody Challenge updatedChallenge) {
        boolean isChallengeUpdated = challengeService.updateChallenge(uuid, updatedChallenge);
        if (isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated successfully.", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Challenge not updated.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/challenge/{uuid}")
    public ResponseEntity<String> deleteChallenge(@PathVariable UUID uuid) {
        boolean isChallengeDeleted = challengeService.deleteChallenge(uuid);
        if (isChallengeDeleted) {
            return new ResponseEntity<>("Challenge deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Challenge not deleted.", HttpStatus.BAD_REQUEST);
        }
    }
}
