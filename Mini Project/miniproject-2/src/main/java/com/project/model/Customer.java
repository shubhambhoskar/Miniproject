package com.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int CustomerLoginId;
	@NotNull
	@Column(unique = true)
	@Email(message="Give proper email")
	private String userName;
	
	private String password;
	
	@Size(min=2,max=15,message="Inavalid firstname")
	private String FirstName;
	
	@Size(min=2,max=15,message="Inavalid firstname")
	private String LastName;
	
	@Pattern(regexp="^[0-9]{10}",message="Incorrect Mobile number")
	private String MobileNumber;
	
	
	@Email(message = "incorrect email")
	@Column(unique = true)
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Address addresses;
}
