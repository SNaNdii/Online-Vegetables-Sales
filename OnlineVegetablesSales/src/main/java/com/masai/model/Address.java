package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private Integer flatNo;
	private String buildingName;
	private String area;
	private String city;
	private String state;
	private String pincode;


}
