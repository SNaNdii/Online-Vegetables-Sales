package com.masai.model;

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
public class VegetableDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer vegId;
	
   
	private String name;
	private String type;
	private Double price;
	private Integer quantity;
	
}
