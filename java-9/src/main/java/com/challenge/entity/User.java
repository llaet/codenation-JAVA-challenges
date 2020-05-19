package com.challenge.entity;

import java.time.LocalDate;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="user")
public class User {

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(max=100)
	@Column(name="full_name")
	private String fullName;
	
	@NotNull
	@Email
	@Size(max=100)
	private String email;
	
	@NotNull
	@Size(max=50)
	private String nickname;
	
	@NotNull
	@Size(max=255)
	private String password;
	
	@CreatedDate
	@NotNull
	@Column(name="created_at")
	private LocalDate createdAt;
	
	@OneToMany
	private List<Candidate> candidates;
	
	@OneToMany
	private List<Submission> submissions;

}
