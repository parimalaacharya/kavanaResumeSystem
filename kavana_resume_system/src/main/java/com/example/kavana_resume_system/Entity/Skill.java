package com.example.kavana_resume_system.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

   

    // New optional fields
    @Column(nullable = true)
    private String level;  // e.g., Beginner, Intermediate, Expert

    @Column(nullable = true)
    private String category;  // e.g., Technical, Soft Skill

    @Column(length = 2000, nullable = true)
    private String description;  // Details about the skill

    // Constructors
    public Skill() {}

    public Skill(String name, User user, String level, String category, String description) {
        this.name = name;
        this.user = user;
        this.level = level;
        this.category = category;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}
