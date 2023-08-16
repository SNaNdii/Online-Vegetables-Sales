package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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