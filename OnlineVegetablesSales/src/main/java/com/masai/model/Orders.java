package com.masai.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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