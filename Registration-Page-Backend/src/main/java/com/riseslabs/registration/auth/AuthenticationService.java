package com.riseslabs.registration.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riseslabs.registration.configuration.JwtService;
import com.riseslabs.registration.entity.RegistrationEntity;
import com.riseslabs.registration.entity.Role;
import com.riseslabs.registration.repository.RegistrationRepository;
import com.riseslabs.registration.service.RegistrationServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	@Autowired
	private final RegistrationRepository registrationRepository;
	
	@Autowired
	private final RegistrationServiceImpl registrationServiceImpl;
	
	@Qualifier("passwordEncoder")
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final JwtService jwtService;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	

	public ResponseEntity<String> register(RegisterRequest request) {
		
		var user = RegistrationEntity.builder()
				.name(request.getName())
				.username(request.getUsername())
				.email (request.getEmail())
				.gender(request.getGender())
				.phone(request.getPhone())
				.createdAt(request.getCreatedAt())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		

		RegistrationEntity regEntity = registrationServiceImpl.adduser(user);

	    if (regEntity != null) 
	        return ResponseEntity.status(HttpStatus.OK).body("{\"status\": true}");
	     else 
	 	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"status\": false}");
				
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {	

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getUsername(),
					request.getPassword()
					)
					);
			
		
		
		var user = registrationRepository.findRegistrationEntityByEmailOrPhoneOrUsername(
				request.getUsername(),
				request.getUsername(),
				request.getUsername()
		    );		
		
		var jwtToken = jwtService.generateToken(user);
		
		
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public List<String> checkExistingFields(String email, String phone, String username) {
		 List<String> existingFields = new ArrayList<>();
	        
	        if (registrationRepository.findRegistrationEntityByEmail(email) != null) {
	            existingFields.add("email");
	        }
	        if (registrationRepository.findRegistrationEntityByPhone(phone) != null) {
	            existingFields.add("phone");
	        }
	        if (registrationRepository.findRegistrationEntityByUsername(username) != null) {
	            existingFields.add("username");
	        }
	        
	        return existingFields;
	}

}
