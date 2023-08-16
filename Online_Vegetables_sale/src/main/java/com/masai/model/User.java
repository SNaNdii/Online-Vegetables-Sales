package com.masai.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class User {

	private String emailId;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
