package com.pola.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pola.domain.LabResult;

@Repository
public interface LabResultRepo extends JpaRepository<LabResult, Long> {

	List<LabResult> findByPetId(long id);
	
	
	
}
