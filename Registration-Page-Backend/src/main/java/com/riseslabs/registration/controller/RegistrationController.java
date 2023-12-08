package com.riseslabs.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.riseslabs.registration.entity.RegistrationEntity;
import com.riseslabs.registration.service.RegistrationService;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("allusers")
	public List<RegistrationEntity> showAll(){
		return registrationService.viewAllUsers();
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("user")
	public ResponseEntity<Boolean> newUser(@RequestBody RegistrationEntity user) {
	    RegistrationEntity newUser = registrationService.adduser(user);
	    if(newUser!=null)
	        return ResponseEntity.ok(true);
	    else
	    	return ResponseEntity.ok(false);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("user/username/{username}")
	public ResponseEntity<Boolean> findByUserName(@PathVariable("username") String username) {
		boolean exists = registrationService.findByUsername(username);
	    return ResponseEntity.ok(exists);
		}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("user/phone/{phone}")
	public ResponseEntity<Boolean> findByPhone(@PathVariable ("phone") String phone) {
		boolean exists = registrationService.findByPhone(phone);
		return ResponseEntity.ok(exists);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("user/mail/{mail}")
	public ResponseEntity<Boolean> findByEmail(@PathVariable("mail") String mail) {
	    boolean exists = registrationService.findByEmail(mail);
	    return ResponseEntity.ok(exists);

	}
	
}