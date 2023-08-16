package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer customerId;
	private String customerName;
	
	private String mobileNumber;
	
	@Embedded
	private Address address;
	
	@Column(unique=true)
	
	
    private String email;
	
    private String password;
    private String confirmPassword;
    
    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL, mappedBy="customer")
    private Cart cart;
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
    private List<Orders> orders=new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
    private List<Feedback> feedbacks=new ArrayList<>();
    

}
