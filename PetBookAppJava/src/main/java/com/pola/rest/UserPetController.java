package com.pola.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pola.domain.MyProfile;
import com.pola.domain.Pet;
import com.pola.domain.User;
import com.pola.exceptions.NotFoundExc;
import com.pola.repository.MyProfileRepo;
import com.pola.repository.PetRepo;
import com.pola.repository.UserRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/userpets")
public class UserPetController {

	@Autowired
	 private PetRepo petRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MyProfileRepo myProfileRepository;
	
	@GetMapping("/{userId}/pets")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Pet>> getThemAll(@PathVariable (value = "userId") Long userId ){
				
		List<Pet> all = petRepo.findByUserId(userId);
		
		return ResponseEntity.ok().body(all);
		
	}
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<User> findCustomersByFirstName(
                               @RequestParam("username") String username) {
 
		return userRepository.findUserByUsername(username);
				
		
//        if (firstName == null) {
//            return customerRepo.findAll(pageable);
//        } else {
//            return customerRepo.findByFirstName(firstName, pageable);
//        }
		
    }
	
	@PostMapping("/{userId}/pets")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addPet(@PathVariable (value = "userId") Long userId, @RequestBody Pet pet){
		
		userRepository.findById(userId).map(user ->{
			
			pet.setUser(user);
			return petRepo.save(pet); 
		}).orElseThrow(() -> new NotFoundExc());
		
		 
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/pets/{petId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updatePet (@PathVariable(value = "petId") long petId, @RequestBody Pet pet){
		
		Pet newPet = petRepo.findById(petId).orElseThrow(() -> new NotFoundExc("Ups, there is no such pet."));
		
		newPet.setName(pet.getName());
		newPet.setAge(pet.getAge());
		newPet.setSpecies(pet.getSpecies());
		newPet.setImagePath(pet.getImagePath());
		
		petRepo.save(newPet);
		
		return ResponseEntity.ok().body("Pet has been updated");
	}
	
	@DeleteMapping("/pets/{petId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> deletePet(@PathVariable(value = "petId") long petId) {
		
		petRepo.deleteById(petId);
		
		return ResponseEntity.ok().body("Pet has been deleted");
		
	}
	
	@GetMapping("/{userId}/getOne")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUserById(@PathVariable (value = "userId") Long userId ){
				
		List<User> friendRequests = userRepository.findUserRequests(userId);
		
		return ResponseEntity.ok().body(friendRequests);
		
	}
	
	
	@GetMapping("/{userId}/myProfile")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<MyProfile> getUserProfile(@PathVariable (value = "userId") Long userId ){
				
		MyProfile myProfile = myProfileRepository.findByUserId(userId);
		
		return ResponseEntity.ok().body(myProfile);
		
	}
	
	
	
}
