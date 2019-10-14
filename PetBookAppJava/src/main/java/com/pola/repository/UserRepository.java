package com.pola.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pola.domain.Friends;
import com.pola.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findUserByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
    @Query("SELECT u FROM User u INNER JOIN Friends f ON f.friendId = u.id WHERE f.userId = :userId and is_friend = false")
	 List<User> findUserRequests(@Param ("userId")long userId);
    
}