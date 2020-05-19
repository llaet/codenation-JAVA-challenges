package com.challenge.entity;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="company")
public class Company {

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(max=100)
	private String name;
	
	@NotNull
	@Size(max=50)
	private String slug;
	
	@CreatedDate
	@NotNull
	@Column(name="created_at")
	private LocalDate createdAt;
	
	@OneToMany
	private List<Candidate> candidates;
}
