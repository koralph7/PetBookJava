package com.pola.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.pola.domain.Post;
import com.pola.repository.PostRepo;

@Service
public class PostService {

	
	@Autowired
	private PostRepo postRepo;

	
//	@Query(
//			  value = "SELECT * FROM post ORDER BY post.createDateTime DESC ", 
//			  nativeQuery = true)
	public List<Post>getPosts(long userId){
		
		List <Post> chhc = postRepo.findByUserId(userId);
		
		Collections.reverse(chhc);
		
		return chhc;
	
	}
	
	
	
}
