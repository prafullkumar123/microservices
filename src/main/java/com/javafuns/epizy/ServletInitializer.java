package com.javafuns.epizy;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * @author Manish kumar ,   Jul 5, 2020
 * Description :
 */
public class ServletInitializer extends SpringBootServletInitializer {
	
	/**
	 * 
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(JavafunsFinalprojectApplication.class);
	}

}
