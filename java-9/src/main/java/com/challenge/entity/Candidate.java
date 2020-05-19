package com.challenge.entity;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="candidate")
public class Candidate	{

	@EmbeddedId
	private CandidateId candidateId;
	
	@NotNull
	private Integer status;
	
	@CreatedDate
	@NotNull
	@Column(name="created_at")
	private LocalDate createdAt;
	
}
