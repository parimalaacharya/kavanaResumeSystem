package com.example.kavana_resume_system.Controller;

import com.example.kavana_resume_system.DTO.AchievementDTO;
import com.example.kavana_resume_system.Entity.Achievement;
import com.example.kavana_resume_system.Entity.User;
import com.example.kavana_resume_system.Repository.AchievementRepository;
import com.example.kavana_resume_system.Repository.UserRepository;
import com.example.kavana_resume_system.Service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;


    // 1️⃣ Get all achievements
    @GetMapping
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    // 2️⃣ Get achievement by ID
    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Achievement not found with id: " + id));
        return ResponseEntity.ok(achievement);
    }

    // 3️⃣ Create new achievement (auto-set logged-in user)
    @PostMapping
    public ResponseEntity<Achievement> createAchievement(
            @RequestBody AchievementDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7); // remove "Bearer "
        String email = jwtService.extractEmail(token); // use your JwtService or JwtUtil

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        

        Achievement achievement = new Achievement();
        achievement.setUser(user); // link logged-in user
        achievement.setTitle(dto.getTitle());
        achievement.setDescription(dto.getDescription());
        achievement.setDate(dto.getDate());
        achievement.setVerificationUrl(dto.getVerificationUrl());

        Achievement saved = achievementRepository.save(achievement);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    // 4️⃣ Update achievement
    @PutMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable Long id,
                                                         @RequestBody AchievementDTO dto) {

        Achievement existing = achievementRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Achievement not found with id: " + id));

        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setDate(dto.getDate());
        existing.setVerificationUrl(dto.getVerificationUrl());

        Achievement updated = achievementRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    // 5️⃣ Delete achievement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        if (!achievementRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Achievement not found with id: " + id);
        }
        achievementRepository.deleteById(id); // ✅ only deletes achievement, not user
        return ResponseEntity.noContent().build();
    }
}