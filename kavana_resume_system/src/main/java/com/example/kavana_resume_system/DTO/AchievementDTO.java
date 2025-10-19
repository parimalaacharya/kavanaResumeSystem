package com.example.kavana_resume_system.DTO;

import java.util.Date;

public class AchievementDTO {

    private Long id;
   
    private String title;
    private String description;
    private Date date;
    private String verificationUrl;
	
	
    // Default Constructor
    public AchievementDTO() {}

    // Parameterized Constructor
    public AchievementDTO(Long id, Long user_id, String title, String description, Date date, String verificationUrl) {
        this.id = id;
       
        this.title = title;
        this.description = description;
        this.date = date;
        this.verificationUrl = verificationUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVerificationUrl() {
        return verificationUrl;
    }

    public void setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }

	
    
}
