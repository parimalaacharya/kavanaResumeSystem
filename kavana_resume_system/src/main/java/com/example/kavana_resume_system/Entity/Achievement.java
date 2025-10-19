package com.example.kavana_resume_system.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;



@Entity
@Table(name = "achievements")
public class Achievement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	 @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;  // ✅ Make sure this exists
	
	public User getUser() {
		return user;
	}

	 public void setUser(User user) {
		 this.user = user;
	 }

	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String verificationUrl;
	
	//Constructors
	//Default Constructor
	public Achievement() {}
	
	//Parameterized Constructors
	public Achievement(Long id, User user, String title,String description,Date date,String verificationUrl) {
		this.id = id;
		
		this.title = title;
		this.description = description;
		this.date = date;
		this.verificationUrl = verificationUrl;
	}
	
	//Getters and Setters

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

