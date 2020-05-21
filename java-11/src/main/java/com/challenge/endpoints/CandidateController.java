package com.challenge.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;


@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;
	
	@Autowired(required = false)
	private CandidateMapper candidateMapper;
	
	@GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable Long userId,@PathVariable Long companyId,@PathVariable Long accelerationId) {
		CandidateDTO candidateDTO = candidateMapper.INSTANCE.map(candidateService.findById(userId, companyId, accelerationId).get());
		if(candidateDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(candidateDTO);
    }

	@GetMapping
    public ResponseEntity<List<CandidateDTO>> findByCompanyOrAccelerationId(
    		@RequestParam(required = false) Long companyId, 
    		@RequestParam(required = false) Long accelerationId) {
		if(companyId != null) {
			return ResponseEntity.ok(candidateMapper.INSTANCE.map(candidateService.findByCompanyId(companyId)));
		}
		return ResponseEntity.ok(candidateMapper.INSTANCE.map(candidateService.findByAccelerationId(accelerationId)));
    }
	
}
