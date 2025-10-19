package com.example.kavana_resume_system.Service;

import com.example.kavana_resume_system.Entity.Achievement;
import java.util.List;
import java.util.Optional;

public interface AchievementService1 {

    Achievement saveAchievement(Achievement achievement);

    List<Achievement> getAllAchievements();

    Optional<Achievement> getAchievementById(Long id);

    Achievement updateAchievement(Long id, Achievement achievementDetails);

    void deleteAchievement(Long id);
}
