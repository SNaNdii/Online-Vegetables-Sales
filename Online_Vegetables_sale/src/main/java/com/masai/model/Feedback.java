package com.masai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer feedbackId;
	private Integer rating;
	private String comments;
	
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer customer;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Orders orders;
    
    

}