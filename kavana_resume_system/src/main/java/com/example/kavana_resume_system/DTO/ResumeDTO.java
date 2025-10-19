package com.example.kavana_resume_system.DTO;

public class ResumeDTO {
    private Long id;
    private String fileName;
    private String fileType;
    private Long userId;

    // No-args constructor
    public ResumeDTO() {}

    // All-args constructor
    public ResumeDTO(Long id, String fileName, String fileType, Long userId) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.userId = userId;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
