package com.challenge.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;


@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

	@Autowired
	private AccelerationService accelerationService;
	

	@GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id) {
		Acceleration acceleration = accelerationService.findById(id).get();
		if(acceleration == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(acceleration);
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam Long companyId) {
    	return ResponseEntity.ok(accelerationService.findByCompanyId(companyId));
    }
	
}
