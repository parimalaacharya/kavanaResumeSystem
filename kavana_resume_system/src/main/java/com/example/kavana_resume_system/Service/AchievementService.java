package com.example.kavana_resume_system.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.kavana_resume_system.Entity.Achievement;
import com.example.kavana_resume_system.Repository.AchievementRepository;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;

    // Constructor Injection
    public AchievementService(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    // ✅ Create a new Achievement
    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    // ✅ Get all Achievements
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    // ✅ Get Achievement by ID
    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    // ✅ Update Achievement by ID
    public Achievement updateAchievement(Long id, Achievement updatedAchievement) {
        return achievementRepository.findById(id)
                .map(achievement -> {
                    achievement.setTitle(updatedAchievement.getTitle());
                    achievement.setDescription(updatedAchievement.getDescription());
                    achievement.setDate(updatedAchievement.getDate());
                    achievement.setVerificationUrl(updatedAchievement.getVerificationUrl());
                    return achievementRepository.save(achievement);
                })
                .orElseThrow(() -> new IllegalArgumentException("Achievement not found with id: " + id));
    }

    // ✅ Delete Achievement by ID
    public void deleteAchievement(Long id) {
        if (!achievementRepository.existsById(id)) {
            throw new IllegalArgumentException("Achievement not found with id: " + id);
        }
        achievementRepository.deleteById(id);
    }
}
