package com.javafuns.epizy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Manish kumar ,   Jul 5, 2020
 * Description : This is appConfig class it is configure encoder class
 */
@Configuration
public class AppConfig {

	/**
	 * @return
	 * Description : it will return BCryptPasswordEncoder object
	 */
	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		
		return new BCryptPasswordEncoder();
	
	}
}
