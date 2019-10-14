package com.pola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pola.domain.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);
	
	
	
}
