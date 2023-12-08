package com.riseslabs.registration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riseslabs.registration.entity.RegistrationEntity;
import com.riseslabs.registration.repository.RegistrationRepository;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationRepository registrationRepository;

	public RegistrationEntity adduser(RegistrationEntity user) {
		return registrationRepository.save(user);
	}

	public List<RegistrationEntity> viewAllUsers() {
		return registrationRepository.findAll();
	}

	public boolean findByPhone(String phone) {
		RegistrationEntity ex = registrationRepository.findRegistrationEntityByPhone(phone);
		if(ex!=null)
			return true;
		else
			return false;
	}

	public boolean findByEmail(String mail) {
		RegistrationEntity ex = registrationRepository.findRegistrationEntityByEmail(mail);
		if(ex!=null)
			return true;
		else
			return false;
	}

	public boolean findByUsername(String username) {
		RegistrationEntity ex = registrationRepository.findRegistrationEntityByUsername(username);
		if(ex!=null)
			return true;
		else
			return false;
	}
}
