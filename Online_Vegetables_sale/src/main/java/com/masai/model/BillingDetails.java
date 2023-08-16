package com.masai.model;

import java.time.LocalDateTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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