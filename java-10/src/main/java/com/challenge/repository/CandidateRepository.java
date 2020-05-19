package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, CandidateId>{

	@Query("SELECT c FROM Candidate c WHERE c.id.user.id = :userId AND c.id.company.id = :companyId AND c.id.acceleration.id = :accelerationId")
    Optional<Candidate> findById(@Param("userId")Long userId, @Param("companyId")Long companyId, @Param("accelerationId")Long accelerationId);

	@Query("SELECT c FROM Candidate c WHERE c.id.company.id = :companyId")
    List<Candidate> findByCompanyId(@Param("companyId") Long companyId);

	@Query("SELECT c FROM Candidate c WHERE c.id.acceleration.id = :accelerationId")
    List<Candidate> findByAccelerationId(@Param("accelerationId") Long accelerationId);
}
