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


	@GetMapping(params = "accelerationName")
	public ResponseEntity<List<User>> findByAccelerationName(@RequestParam String accelerationName) {
		return ResponseEntity.ok(userService.findByAccelerationName(accelerationName));
	}

	@GetMapping(params = "companyId")
	public ResponseEntity<List<User>> findByCompanyId(@RequestParam Long companyId) {
		return ResponseEntity.ok(userService.findByCompanyId(companyId));
	}

}
