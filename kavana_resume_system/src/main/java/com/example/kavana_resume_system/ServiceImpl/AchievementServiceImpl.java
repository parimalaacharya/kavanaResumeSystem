package com.example.kavana_resume_system.ServiceImpl;

import com.example.kavana_resume_system.Entity.Achievement;
import com.example.kavana_resume_system.Repository.AchievementRepository;
import com.example.kavana_resume_system.Service.AchievementService;
import com.example.kavana_resume_system.Service.AchievementService1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementServiceImpl implements AchievementService1 {

    @Autowired
    private AchievementRepository achievementRepository;

    public Achievement saveAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    public Achievement updateAchievement(Long id, Achievement achievementDetails) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));

        achievement.setTitle(achievementDetails.getTitle());
        achievement.setDescription(achievementDetails.getDescription());
        achievement.setDate(achievementDetails.getDate());
        // add any other fields here

        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        Achievement achievement = achievementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achievement not found with id: " + id));
        achievementRepository.delete(achievement);
    }
}
