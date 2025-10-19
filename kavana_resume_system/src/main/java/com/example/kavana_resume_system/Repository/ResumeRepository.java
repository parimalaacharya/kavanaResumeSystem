package com.example.kavana_resume_system.Repository;

import com.example.kavana_resume_system.Entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    // If you want to fetch resumes by user
    List<Resume> findByUserId(Long userId);
}
