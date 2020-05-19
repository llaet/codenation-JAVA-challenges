package com.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Challenge;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long>{

	@Query("SELECT DISTINCT c.id.acceleration.challenge FROM Candidate c WHERE c.id.acceleration.id = :accelerationId AND c.id.user.id = :userId")
	List<Challenge> findByAccelerationIdAndUserId(@Param("accelerationId") Long accelerationId, @Param("userId") Long userId);
}
