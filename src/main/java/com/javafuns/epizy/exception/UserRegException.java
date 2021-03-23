/**
 * @Copyright javaFuns Pvt Ltd .All rights Reserved 2020. You should not disclose this information
 * Otherwise terms and conditions will be applied.
 */
package com.javafuns.epizy.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Manish kumar ,   Jun 18, 2020
 * Description :
 */
@SuppressWarnings("serial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegException extends Exception {
	
	private String respCode;
	/*
	 * creating single arg contructer for respMsg property
	 */
	@NonNull
	private String respMsg;

}
