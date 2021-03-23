package com.javafuns.epizy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Manish kumar ,   Jul 5, 2020
 * Description : This is starter point of the application
 */
@SpringBootApplication
public class JavafunsFinalprojectApplication {

	/**
	 * 
	 * @param args
	 * Description :Main method entry point  of application 
	 */
	public static final void main(final String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(JavafunsFinalprojectApplication.class, args);
	}

}
