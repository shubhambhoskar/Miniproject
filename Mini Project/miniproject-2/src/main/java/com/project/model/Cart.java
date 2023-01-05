package com.project.model;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO )
	private Integer cartId;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Customer customer;
	
	private Integer Amount;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JsonIgnore
	private Map<Product, Integer> products;	
	
}
