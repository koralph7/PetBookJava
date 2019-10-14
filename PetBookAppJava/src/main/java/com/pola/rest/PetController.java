package com.pola.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pola.domain.Pet;
import com.pola.exceptions.NotFoundExc;
import com.pola.repository.PetRepo;
import com.pola.security.services.UserPrinciple;
import com.pola.service.PetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PetController {

	
	@Autowired
	 private PetService userPetService;
	
	@Autowired
	 private PetRepo petRepo;
	
	@GetMapping("/pets")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Pet>> getThemAll(){
				
		List<Pet> all = petRepo.findAll();
		
		return ResponseEntity.ok().body(all);
		
	}
	

//	@PostMapping("/pets")
//	public ResponseEntity<?> addPet(@RequestBody Pet pet){
//		
//		petRepo.save(pet);  
//		
//		return ResponseEntity.ok().build();
//	}
	
	@GetMapping("/pets/{id}")
	public ResponseEntity<Pet> getOne(@PathVariable("id") long id, Authentication authentication) {
		
	
		
		Pet pet = userPetService.getOnePet(id, authentication);
		
		return ResponseEntity.ok().body(pet);
		
	}
	
	@PutMapping("/pets/{id}")
	public Pet updateEmployees (@PathVariable("id") long id, @RequestBody Pet pet){
		
		return petRepo.findById(id).map(newPet -> {
			newPet.setName(pet.getName());
			newPet.setAge(pet.getAge());
			newPet.setSpecies(pet.getSpecies());
			newPet.setImagePath(pet.getImagePath());
            return petRepo.save(pet);
        }).orElseThrow(() -> new NotFoundExc("You suck"));
    }
	}
	

