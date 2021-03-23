/*
 * @Copyright javaFuns Pvt Ltd .All rights Reserved 2020. You should not disclose this information
 * Otherwise terms and conditions will be applied.
 */
package com.javafuns.epizy.controller;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javafuns.epizy.exception.UserRegException;
import com.javafuns.epizy.model.UserRegReq;
import com.javafuns.epizy.model.UserRegResp;

/**
 * @author Manish kumar ,   Jul 23, 2020
 * Description :
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationRestControllerTest {

	@Autowired
	private RegistrationRestController controller;

	@Autowired
	private MockMvc mockMvc;

	/**
	 * @param mockReg
	 * @return
	 * Description : To Convert Object to Json
	 */
	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}


	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/rest/")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Hello, World")));
	}

	@Test
	public void testUserRegResp_positive() throws Exception {

		UserRegReq realReg  = new UserRegReq("prafull", "998188", "test@gmail.com", "testUser", "test");
		UserRegResp realResp = new UserRegResp("User Registerd");
		String uri = "/rest/save";
		String inputJson = mapToJson(realReg);
		String outputJson = mapToJson(realResp);

		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
		.andExpect(status().isOk())
		.andExpect(content().json(outputJson))
		;

	}
	
	@Test()
	public void testUserRegResp_Negetive() throws Exception {

		UserRegReq realReg  = new UserRegReq();
		UserRegResp realResp = new UserRegResp("request object is null or empty");
		String uri = "/rest/save";
		String inputJson = mapToJson(realReg);
		String outputJson = mapToJson(realResp);

		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
		.andExpect(status().isOk())
		.andExpect(content().json(outputJson))
		;



	}

}
