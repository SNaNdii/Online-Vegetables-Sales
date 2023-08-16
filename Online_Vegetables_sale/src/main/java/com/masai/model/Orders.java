package com.masai.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderNo;
	
	@ElementCollection
	@Embedded
	private Set<DemandingVegetableDTO> demandingVegetableDto=new HashSet<>();
	
	private Double totalAmount;
	private String status;
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customerId")
	@JsonIgnore
	private Customer customer;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy= "order")
	@JsonIgnore
	private BillingDetails billingDetail;
	
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy= "orders")
	@JsonIgnore
	private Feedback feedback;

}
