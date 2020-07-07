package com.bazooka.jobhunter.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
				  property = "id")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="Positon can't be blank")
	private String position;
	
	@NotBlank(message="Location can't be blank")
	private String location;
	
	@NotBlank(message="Company can't be blank")
	private String company;
	
	private String category = "Interested";
	
	@Email(message="Email is invalid")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String manager;
	
	private String description;
	
	private String link;
	
	@Column(name="created_at", updatable = false)
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;
	
	@OneToMany(mappedBy="job",
			   cascade= CascadeType.ALL,
			   orphanRemoval = true)
	private List<Note> notes = new ArrayList<>();
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
