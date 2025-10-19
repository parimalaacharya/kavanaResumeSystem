package com.example.kavana_resume_system.Service;

import com.example.kavana_resume_system.DTO.ResumeDTO;
import com.example.kavana_resume_system.Entity.Resume;
import java.util.List;

public interface ResumeService {
    Resume saveResume(Resume resume);
    List<Resume> getAllResumes();
    Resume getResumeById(Long id);
    void deleteResume(Long id);
    List<Resume> getResumesByUserId(Long userId);
    
    
    ResumeDTO convertToDTO(Resume resume);
    List<ResumeDTO> convertToDTOList(List<Resume> resumes);
}
