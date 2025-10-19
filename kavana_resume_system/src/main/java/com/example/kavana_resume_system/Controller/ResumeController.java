package com.example.kavana_resume_system.Controller;

import com.example.kavana_resume_system.Entity.Resume;
import com.example.kavana_resume_system.DTO.ResumeDTO;
import com.example.kavana_resume_system.Service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    // Upload resume
    @PostMapping("/upload")
    public ResumeDTO uploadResume(@RequestParam("file") MultipartFile file, 
                                  @RequestParam("userId") Long userId) throws IOException {
        Resume resume = new Resume();
        resume.setFileName(file.getOriginalFilename());
        resume.setFileType(file.getContentType());
        resume.setData(file.getBytes());
        resume.setUserId(userId);

        Resume saved = resumeService.saveResume(resume);
        return resumeService.convertToDTO(saved);
    }

    // Get all resumes (DTO only)
    @GetMapping("/all")
    public List<ResumeDTO> getAllResumes() {
        return resumeService.convertToDTOList(resumeService.getAllResumes());
    }

    // Get resume by ID (DTO)
    @GetMapping("/{id}")
    public ResponseEntity<ResumeDTO> getResumeById(@PathVariable Long id) {
        Resume resume = resumeService.getResumeById(id);
        if (resume == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(resumeService.convertToDTO(resume));
    }

    // Get resumes by user ID (DTO)
    @GetMapping("/user/{userId}")
    public List<ResumeDTO> getResumesByUser(@PathVariable Long userId) {
        return resumeService.convertToDTOList(resumeService.getResumesByUserId(userId));
    }

    // Delete resume
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable Long id) {
        Resume resume = resumeService.getResumeById(id);
        if (resume == null) return ResponseEntity.notFound().build();

        resumeService.deleteResume(id);
        return ResponseEntity.ok("Resume deleted successfully!");
    }

    // Download resume file (raw byte[])
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadResume(@PathVariable Long id) {
        Resume resume = resumeService.getResumeById(id);
        if (resume == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + resume.getFileName() + "\"")
                .header("Content-Type", resume.getFileType())
                .body(resume.getData());
    }
}
