package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class BillingDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer billingId;
	
	@Enumerated(EnumType.STRING)
	private TransactionMode transactionMode;
	private LocalDateTime transactionDate;
	
	
	private String transactionStatus;
	
	@Embedded
	private Address billingAddress;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="orderId")
	private Orders order;
	
	
	
}