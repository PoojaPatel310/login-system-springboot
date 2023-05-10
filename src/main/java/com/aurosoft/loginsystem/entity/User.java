package com.aurosoft.loginsystem.entity;

import java.util.Date;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id" , nullable = false )
	private int id;
	
	@Column(name= "fname" , nullable = false ,length = 50)
	private String fname;
	
	@Column(name= "lname" , nullable = false ,length = 50)
	private String lname;
	
	@Column(name= "email" , nullable = false ,length = 50)
	private String email;
	
	@Column(name= "password" , nullable = false ,length = 50)
	private String password;
	
	@Column(name= "phone" , nullable = false ,length = 15)
	private String phone;

	@Column(name= "role" , nullable = false ,length = 15)
	private String role;
	@Column(name= "vcode" , nullable = false ,length = 10)
	private String vcode;


	
	@Temporal(TemporalType.DATE)
	@Column(name= "reg_date",nullable = false )
	private Date regDate;

}
