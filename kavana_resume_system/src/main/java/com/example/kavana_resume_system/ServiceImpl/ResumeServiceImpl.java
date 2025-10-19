package com.example.kavana_resume_system.ServiceImpl;

import com.example.kavana_resume_system.DTO.ResumeDTO;
import com.example.kavana_resume_system.Entity.Resume;
import com.example.kavana_resume_system.Repository.ResumeRepository;
import com.example.kavana_resume_system.Service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public List<Resume> getResumesByUserId(Long userId) {
        return resumeRepository.findByUserId(userId);
    }
    

    // ----------------- DTO methods -----------------

    @Override
    public ResumeDTO convertToDTO(Resume resume) {
        if (resume == null) return null;
        return new ResumeDTO(
                resume.getId(),
                resume.getFileName(),
                resume.getFileType(),
                resume.getUserId()
        );
    }

    @Override
    public List<ResumeDTO> convertToDTOList(List<Resume> resumes) {
        if (resumes == null || resumes.isEmpty()) return List.of();
        return resumes.stream()
                      .map(this::convertToDTO)
                      .collect(Collectors.toList());
    }
}
