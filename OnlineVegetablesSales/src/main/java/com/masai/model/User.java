package com.masai.model;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data
public class User {

	private String emailId;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
