package com.javafuns.epizy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegReq {

	private String name;
	private String mobile;
	private String email;
	private String userName;
	private String pwd;
}
