package com.pola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pola.domain.Friends;

public interface FriendRepo extends JpaRepository<Friends, Long>{

	 List<Friends> findByUserId(long userId); 
	 
	 Friends findByFriendId(long friendId); 
	
	 @Query("SELECT f FROM Friends f WHERE f.userId = :userId")
	 List<Friends> findUserFriends(@Param ("userId")long userId);
	 
	 @Query("SELECT f FROM Friends f WHERE f.userId = :userId and isFriend = false")
	 List<Friends> findUserRequests(@Param ("userId")long userId);
	 
	 @Query("SELECT f.friendId FROM Friends f WHERE f.userId = :userId and isFriend = false")
	 List<Long> findFriendsIds(@Param ("userId")long userId);
	 
	 @Query("SELECT f FROM Friends f WHERE f.userId = :userId and f.friendId =:friendId")
	 Friends findIfFriends(@Param ("userId")long userId, @Param ("friendId")long friendId);
	 
	 @Query("select new java.lang.Boolean(count(*) > 0)  FROM Friends WHERE userId = :userId and friendId =:friendId and isFriend = true")
	 Boolean findIftheyFriends(@Param ("userId")long userId, @Param ("friendId")long friendId);
	 
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM Friends f WHERE userId = :userId and friendId =:friendId")
	 Integer deleteRequest(@Param ("userId")long userId, @Param ("friendId")long friendId);
	 
	 @Transactional
	 @Modifying
	 @Query("UPDATE Friends f SET is_friend = true WHERE userId = :userId and friendId =:friendId")
	 Integer acceptRequest(@Param ("userId")long userId, @Param ("friendId")long friendId);
}
