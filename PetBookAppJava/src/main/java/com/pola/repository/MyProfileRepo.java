package com.pola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pola.domain.MyProfile;
import com.pola.domain.Pet;

public interface MyProfileRepo extends JpaRepository<MyProfile, Long> {

	MyProfile findByUserId(Long userId);
	
	
}
