package com.pola.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "pet")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pet {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private int age;
	private String species;
	
	@Column(name="image_path")
	private String imagePath;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
    @JsonProperty("userId")
	@JsonIgnore
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    @JsonProperty("userId")
	private User user;
	
	
	public Pet() {
		
	}
	
	
	
	
	
//	public Pet(String name, int age, String species, String imagePath) {
//		super();
//		this.name = name;
//		this.age = age;
//		this.species = species;
//		this.imagePath = imagePath;
//	}

	public Pet(String name, int age, String species, String imagePath) {
		super();
		this.name = name;
		this.age = age;
		this.species = species;
		this.imagePath = imagePath;
		
	}



	public Long getId() {
		return id;
	}
	




	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}




	@JsonIgnore
	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	








	
	
	
	
}