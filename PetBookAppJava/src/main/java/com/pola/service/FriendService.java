//package com.pola.service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Service;
//
//import com.pola.model.Friends;
//import com.pola.repository.FriendRepo;
//
//@Service
//public abstract class FriendService {
//
//	@Autowired
//	private FriendRepo friendRepo;
//	
//	public Friends isFriend(Long userId, Long friendId) {
//		
//		List<Friends> friends = friendRepo.findByUserId(userId);
//		
//		if (friends.contains(friendId)) {
//			
//			return friendRepo.findByFriendId(friendId);
//			
//		}
//		
//		else {
//			
//			return null;
//			
//		}
//		
//		
//		
//		
//	}
//	
////	@Query("SELECT u FROM Friends u WHERE u.friendId = :userId")
////	public abstract List<Friends> findAllFriends(Long userId);
//	
//}
