package com.pola.rest;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pola.domain.Friends;
import com.pola.domain.Pet;
import com.pola.domain.Post;
import com.pola.exceptions.NotFoundExc;
import com.pola.message.response.ResponseMessage;
import com.pola.repository.FriendRepo;
//import com.pola.service.FriendService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FriendController {

	
	@Autowired
	private FriendRepo friendRepo;
	
	
//	@Autowired
//	private FriendService friendService;
	
	@PostMapping("/friends")
	public ResponseEntity<Friends> addFriend( @RequestBody Friends friend) {
		
		
		
				
		friend.setFriend(false);
		
		friendRepo.save(friend);
		
		return ResponseEntity.ok().build();
		
	}
	
	@PatchMapping("/helperFordddAccepting/{userId}/{friendId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Friends> aaccept(@PathVariable (value = "userId") Long userId, @RequestBody Friends friend, @PathVariable (value = "friendId") Long friendId) 
	{
		
		friend = friendRepo.findIfFriends(userId, friendId);
		
		friend.setFriend(true);
		
		friendRepo.save(friend);
		
		return ResponseEntity.ok().body(friend);
		
	}
	
	@PutMapping("/helperForAccepting/{userId}/{friendId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Friends> findFriendship(@PathVariable (value = "userId") Long userId, @RequestBody Friends friend, @PathVariable (value = "friendId") Long friendId) 
	{
		
		friend = friendRepo.findIfFriends(userId, friendId);
		
		friend.setFriend(true);
		
		friendRepo.save(friend);
		
		return ResponseEntity.ok().body(friend);
		
	}
	
//	@PutMapping("/friends")
//	public ResponseEntity<Friends> addddFriend( @RequestBody Friends friend) {
//		
//		
//		
//				
//		friend.setFriend(false);
//		
//		friendRepo.save(friend);
//		
//		return ResponseEntity.ok().build();
//		
//	}
	
//	@GetMapping("/if/{userId}/{friendId}")
//	public Map<String, Boolean>  ifFriends(@PathVariable(value = "userId") Long userId, @PathVariable (value = "friendId")Long friendId) {
//		
//		Boolean res = 
//		
//		if(friendService.isFriend(userId, friendId)==true)
//		
//		return Collections.singletonMap("success", true);
//		else {
//			return Collections.singletonMap("success", false);
//		}
		
		
//	}
	
	@GetMapping("/friendsListttt/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Friends>> listofofFriends(@PathVariable (value = "userId") Long userId) 
	{
		List<Friends> all = friendRepo.findUserFriends(userId);
		
		return ResponseEntity.ok().body(all);
	}
	
	@GetMapping("/friendsRequest/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Friends>> listofRequests(@PathVariable (value = "userId") Long userId) 
	{
		
		List<Friends> all = friendRepo.findUserRequests(userId);
		
		return ResponseEntity.ok().body(all);
		
	}
	
	
	
	
//	@GetMapping("/friend/{userId}/{friendId}")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//	public ResponseEntity<Friends> listofoFriends(@PathVariable (value = "userId") Long userId, @PathVariable (value = "friendId") Long friendId) 
//	{
//		Friends all = friendRepo.findIfFriends(userId, friendId);
//		
//		return ResponseEntity.ok().body(all);
//	}
	
	
	@RequestMapping(value = "/checkIfRequested/{userId}/{friendId}", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isRequested(@PathVariable (value = "userId") Long userId, @PathVariable (value = "friendId") Long friendId) {
	 
		Friends hihi = friendRepo.findIfFriends(userId, friendId);
		
		if (hihi ==null) {
			
			return false;
			
		}
 	
		
		else return true;
		
	}
	
	@RequestMapping(value = "/checkIfFriends/{userId}/{friendId}", method = RequestMethod.GET)
	@ResponseBody
	public Boolean isFriend(@PathVariable (value = "userId") Long userId, @PathVariable (value = "friendId") Long friendId) {
	 
		if (friendRepo.findIftheyFriends(userId, friendId)) {
			
			return true;
			
		}
		
		else {
			return false;
		}
		
	}
	
	
	
	
	
//	@RequestMapping(value = "/hihi", method = RequestMethod.GET)
//	@ResponseBody
//	public Boolean isfrie() {
//	 
//	return false;
//		
//	}
	
	
	
	@GetMapping("/friendsList/{userId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<Friends>> listFriends(@PathVariable (value = "userId") Long userId) 
	{
		List<Friends> all = friendRepo.findByUserId(userId);
		
		return ResponseEntity.ok().body(all);
	}
	
	@PutMapping(path ="/acceptRequest/{userId}/{friendId}", consumes =MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> acceptRequest (@PathVariable(value = "userId") long userId,  @PathVariable(value = "friendId") long friendId){
		
		friendRepo.acceptRequest(userId, friendId);
		
		return ResponseEntity.ok().body("Request has been accepted");
	}
	
	@DeleteMapping("/deleteFriend/{userId}/{friendId}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteRequest(@PathVariable(value = "userId") long userId, @PathVariable(value = "friendId") long friendId) {
		
		friendRepo.deleteRequest(userId, friendId);
		
		return ResponseEntity.ok().build();
		
	}
	
}
