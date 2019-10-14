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
import org.springframework.web.bind.annotation.RestController;

import com.pola.domain.Pet;
import com.pola.domain.Post;
import com.pola.exceptions.NotFoundExc;
import com.pola.repository.PostRepo;
import com.pola.repository.UserRepository;
import com.pola.service.PostService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{userId}/posts")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Post>> getThemAll(@PathVariable (value = "userId") Long userId ){
				
		List<Post> all = postService.getPosts(userId);
		
		
		return ResponseEntity.ok().body(all);
		
	}
	
	
	
	
	
	@PostMapping("/{userId}/post")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addPost(@PathVariable (value = "userId") Long userId, @RequestBody Post post){
		
		userRepository.findById(userId).map(user ->{
			
			post.setUser(user);
			return postRepo.save(post); 
		}).orElseThrow(() -> new NotFoundExc());
		
		 
		
		return ResponseEntity.ok().build();
	}
	
//	@PutMapping("/pets/{petId}")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//	public ResponseEntity<?> updatePost (@PathVariable(value = "petId") long petId, @RequestBody Pet pet){
//		
//		Pet newPet = petRepo.findById(petId).orElseThrow(() -> new NotFoundExc("Ups, there is no such pet."));
//		
//		newPet.setName(pet.getName());
//		newPet.setAge(pet.getAge());
//		newPet.setSpecies(pet.getSpecies());
//		newPet.setImagePath(pet.getImagePath());
//		
//		petRepo.save(newPet);
//		
//		return ResponseEntity.ok().body("Pet has been updated");
//	}
	
	@DeleteMapping("/pets/{postId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> deletePost(@PathVariable(value = "postId") long postId) {
		
		postRepo.deleteById(postId);
		
		return ResponseEntity.ok().body("Post has been deleted");
		
	}
	
	
}
