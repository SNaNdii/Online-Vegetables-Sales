package com.masai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer adminId;
	private String name;
	private String contactNumber;
	
	@Column(unique=true)
	private String email;
	private String password;
	
}