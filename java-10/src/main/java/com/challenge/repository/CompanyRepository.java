package com.challenge.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.challenge.entity.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long>{
	
	@Query("SELECT DISTINCT c.id.company FROM Candidate c WHERE c.id.acceleration.id = :accelerationId")
	List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);
	
	@Query("SELECT c.id.company FROM Candidate c WHERE c.id.user.id = :userId")
	List<Company> findByUserId(@Param("userId") Long userId);
}
