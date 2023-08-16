package com.masai.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DemandingVegetableDTO {
	
	private String name;
	private String type;
	private Double price;
	private Integer quantity;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandingVegetableDTO other = (DemandingVegetableDTO) obj;
		return Objects.equals(name, other.name) && Objects.equals(type, other.type);
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, type);
	}
	
	
	
	
	
}
