package com.javafuns.epizy.filter;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.javafuns.epizy.jwtUtil.JWTUtil;

@Configuration
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil util;

	@Autowired
	private UserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. read token from Request  
		String token = request.getHeader("Authorization");

		//2. If token is not null then goto jWt util 
		if (token != null) {

			//3. get Subject(username)
			String username = util.getUsername(token);

			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

				//4. communication with DB
				//5. Get Spring Security user Object 
				UserDetails user = service.loadUserByUsername(username);

				//5. (a) Optional validate DB user and subject matching or not 
				boolean isValid = util.validateToken(token, user.getUsername());

				if(isValid) {
					//6. Create Authentication Object 
					UsernamePasswordAuthenticationToken authentiaction = new UsernamePasswordAuthenticationToken(
							user.getUsername(), user.getPassword(), user.getAuthorities()
							);

					//link with current request 
					authentiaction.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					//also link with SecurityContext
					SecurityContextHolder.getContext().setAuthentication(authentiaction);

				}//isValide
			}//userName!=null
		}//token!=null

		filterChain.doFilter(request, response);

	}//filter Method

}
