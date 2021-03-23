/**
 * @Copyright javaFuns Pvt Ltd .All rights Reserved 2020. You should not disclose this information
 * Otherwise terms and conditions will be applied.
 */
package com.javafuns.epizy.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javafuns.epizy.exception.UserRegException;
import com.javafuns.epizy.model.UserRegReq;

/**
 * @author Manish kumar ,   Jun 18, 2020
 * Description :
 */
@Component
public class UserRegValidator {
	
	/**
	 * This is default constructor that is required create object 
	 * of UserRegValidator
	 */ 
	public UserRegValidator() {
		// TODO Auto-generated constructor stub
		System.out.println("Entered into validator");
	}
	
	/**
	 * 
	 * Description : 
	 * @throws UserRegException 
	 */
	public void userRegvalidateRequest(UserRegReq userRegReq) throws UserRegException {
		// TODO Auto-generated method stub
		if( userRegReq == null || userRegReq.getName() == null || userRegReq.getMobile() == null ||
				userRegReq.getEmail() == null || userRegReq.getUserName() == null || userRegReq.getPwd() == null ) {
			throw new UserRegException("userReg01","request object is null or empty");
		}
	}

}
