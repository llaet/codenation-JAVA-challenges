package com.challenge.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Embeddable
@EntityListeners(AuditingEntityListener.class)
public class SubmissionId implements Serializable {

	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName="id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="challenge_id",referencedColumnName="id")
	private Challenge challenge;
}
