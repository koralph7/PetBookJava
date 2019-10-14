package com.pola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pola.domain.LabResult;
import com.pola.domain.Pet;

@Repository
public interface PetRepo extends JpaRepository<Pet, Long>{

	List<Pet> findByUserId(Long userId);
}
