package com.challenge.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class CandidateId implements Serializable{

	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="acceleration_id",referencedColumnName="id")
	private Acceleration acceleration;
	
	@ManyToOne
	@JoinColumn(name="company_id",referencedColumnName="id")
	private Company company;
}
