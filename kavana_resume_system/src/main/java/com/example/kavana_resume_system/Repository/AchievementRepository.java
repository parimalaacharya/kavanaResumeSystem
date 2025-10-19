package com.example.kavana_resume_system.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.kavana_resume_system.Entity.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    
}
