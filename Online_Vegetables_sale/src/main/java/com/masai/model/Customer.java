package com.masai.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
