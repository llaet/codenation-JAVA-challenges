package com.challenge.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;


@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id) {
		Company company = companyService.findById(id).get();
		if(company == null) {
			return ResponseEntity.notFound().build();
		}
        return ResponseEntity.ok(company);
    }

	@GetMapping
    public ResponseEntity<List<Company>> findByAccelerationOrUserId(
    		@RequestParam(required = false) Long accelerationId, 
    		@RequestParam(required = false) Long userId) {
		if(accelerationId != null) {
			return ResponseEntity.ok(companyService.findByAccelerationId(accelerationId));
		}
        return ResponseEntity.ok(companyService.findByUserId(userId));
    }
	
}
