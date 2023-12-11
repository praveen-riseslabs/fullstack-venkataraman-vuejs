package com.riseslabs.registration.demo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riseslabs.registration.entity.RegistrationEntity;
import com.riseslabs.registration.service.RegistrationServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/mainController")
public class DemoController {
	
	private final RegistrationServiceImpl registrationServiceImpl;
	
	@GetMapping("/allUsers")
	@PreAuthorize("hasRole('USER')")
	public List<RegistrationEntity> getAllUsers(){
		return registrationServiceImpl.viewAllUsers();
	}
	
	@GetMapping("/sample")
	public String hello(){
		return "hello world";
	}

}


