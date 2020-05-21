package com.challenge.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;



@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") Long userId) {
		User user = userService.findById(userId).get();
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<List<User>> findByAccelerationNameOrCompanyId(
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) Long companyId) {
		if(companyId != null) {
			return ResponseEntity.ok(userService.findByCompanyId(companyId));
		}
		return ResponseEntity.ok(userService.findByAccelerationName(name));
	}

}
