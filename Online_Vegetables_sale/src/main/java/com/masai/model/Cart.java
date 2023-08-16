package com.masai.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cartId;
	
	@ElementCollection
	@Embedded
	private Set<DemandingVegetableDTO> demandingVegetableDto=new HashSet<>();
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customerId")
	private Customer customer;

    
	
	
}