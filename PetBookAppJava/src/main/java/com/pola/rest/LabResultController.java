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
import org.springframework.web.bind.annotation.RestController;

import com.pola.domain.LabResult;
import com.pola.domain.Pet;
import com.pola.exceptions.NotFoundExc;
import com.pola.repository.LabResultRepo;
import com.pola.repository.PetRepo;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LabResultController {

	@Autowired
	private PetRepo petRepo;
	
	@Autowired
	private LabResultRepo labResultRepo;
	
	@GetMapping("/pets/{petId}/labResult")
	public List<LabResult> getAllResaultsOfPet(@PathVariable (value = "petId") Long petId){
		
		return labResultRepo.findByPetId(petId);
		
	}
	

	@GetMapping("/labResult/{labResultId}")
	public ResponseEntity<LabResult> getLabResult(@PathVariable (value = "labResultId") Long labResultId){
		
		LabResult labRes =labResultRepo.findById(labResultId).orElseThrow(() -> new NotFoundExc("Ooo, such labResult doesn't exist"));
		
		return ResponseEntity.ok().body(labRes);
			
		
	}
	
	
	@PostMapping("/{petId}/labResults")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addlabResults(@PathVariable (value = "petId") Long petId, @RequestBody LabResult labResult){
		
		petRepo.findById(petId).map(pet ->{
			
			labResult.setPet(pet);
			return labResultRepo.save(labResult); 
		}).orElseThrow(() -> new NotFoundExc());
		
		 
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/labResult/{labResultId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> updateLabs (@PathVariable(value = "labResultId") long labResultId, @RequestBody LabResult labResult){
		
		LabResult newLabResult = labResultRepo.findById(labResultId).orElseThrow(() -> new NotFoundExc("Ups, there is no such lab."));
		
		newLabResult.setParameter(labResult.getParameter());
		newLabResult.setValue(labResult.getValue());
		
		labResultRepo.save(newLabResult);
		
		
		
		return ResponseEntity.ok().body("Pet has been updated");
	}
	
	@DeleteMapping("/labResult/{labResultId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteLabResult(@PathVariable(value = "labResultId") long labResultId) {
		
		labResultRepo.deleteById(labResultId);
		
		return ResponseEntity.ok().body("Pet has been deleted");
		
	}
}
