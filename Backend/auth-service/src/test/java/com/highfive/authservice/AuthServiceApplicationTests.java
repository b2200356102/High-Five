package com.highfive.authservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.highfive.authservice.entity.Department;
import com.highfive.authservice.entity.User;

@SpringBootTest
class AuthServiceApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mvc;

	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void addDepartment() throws Exception {
		String uri = "/api/departments/";
		Department department = new Department(null, "Computer Science");
		String request = mapToJson(department);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(request))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);
	}

	@Test
	public void addStudent() throws Exception {
		String uri = "/api/users/1/";
		User user = new User("2210356135", "Ataberk ASAR", "aasar", "12345", "student",
				true);
		String request = mapToJson(user);

		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post(uri)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(request))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();

		assertEquals(status, 200);
	}
}
