package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CurrentCustomerUserSession {
	
    @Id
	private Integer userId;
	
	@Column(unique=true)
	private String uuid;
	
	private LocalDateTime localDateTime;
	

}