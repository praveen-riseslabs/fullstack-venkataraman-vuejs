package com.riseslabs.registration.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riseslabs.registration.entity.RegistrationEntity;
import com.riseslabs.registration.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl{
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	
	public RegistrationEntity adduser(RegistrationEntity registrationEntity) {		

			return registrationRepository.save(registrationEntity);

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
	
	public boolean getUser(String username) {
		RegistrationEntity Uname = registrationRepository.findRegistrationEntityByUsername(username);
		RegistrationEntity Email = registrationRepository.findRegistrationEntityByEmail(username);
		RegistrationEntity Phone = registrationRepository.findRegistrationEntityByPhone(username);
		if(Uname!=null) {
			return true;
		}else if(Email != null){
			return true;
		}else if(Phone != null){
			return true;
		}else {
			return false;
		}
	}
	
	public RegistrationEntity findUser(String username) {
		RegistrationEntity Uname = registrationRepository.findRegistrationEntityByUsername(username);
		RegistrationEntity Email = registrationRepository.findRegistrationEntityByEmail(username);
		RegistrationEntity Phone = registrationRepository.findRegistrationEntityByPhone(username);
		if(Uname!=null) {
			return Uname;
		}else if(Email != null){
			return Email;
		}else if(Phone != null){
			return Phone;
		}else {
			return null;
		}
	}
	
//	}
}
