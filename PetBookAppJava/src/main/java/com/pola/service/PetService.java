package com.pola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.pola.domain.Pet;
import com.pola.domain.User;
import com.pola.exceptions.NotFoundExc;
import com.pola.repository.PetRepo;
import com.pola.security.services.UserPrinciple;

@Service
public class PetService {

	
	
	
	
	@Autowired
	private PetRepo petRepo;
	
	
	
	public Pet getOnePet(long id, Authentication authentication) {
		
		
		
		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
		
		
		
		userPrincipal.getId();
		
		Pet pet = petRepo.findById(id).orElseThrow(() -> new NotFoundExc("Oups, id is wrong!"));
		
		User user = pet.getUser();
		
		if (user.getId() == userPrincipal.getId()) {
			return pet;
		}
		
		else
			
			System.out.println("jhsjsh");
		return null;
		

	}
	
}
