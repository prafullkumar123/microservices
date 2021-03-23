package com.javafuns.epizy.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	@Id
	private int id;
	@NonNull
	private String name;
	@NonNull
	private String mobile;
	@NonNull
	private String email;
	@NonNull
	private String userName;
	@NonNull
	private String pwd;
	private Date dateOfReg;
	private String roles;
	private String userStatus;

}
