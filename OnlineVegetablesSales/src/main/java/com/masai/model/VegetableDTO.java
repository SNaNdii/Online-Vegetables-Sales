package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class VegetableDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vegId;
	
   
	private String name;
	private String type;
	private Double price;
	private Integer quantity;
	
}
