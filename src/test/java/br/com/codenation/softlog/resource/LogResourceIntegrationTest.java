package br.com.codenation.softlog.resource;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.codenation.softlog.dto.request.LoginRequestDTO;
import br.com.codenation.softlog.security.jwt.CredencialsJWT;
import br.com.codenation.softlog.security.jwt.SecurityJWT;

@SpringBootTest
@AutoConfigureMockMvc
class LogResourceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;	

	@Autowired
	private SecurityJWT securityJWT;
	
	@Test
	void testSearchAutenticado() throws Exception {

		CredencialsJWT credencials = securityJWT.createCredencials("admin", "admin@codenation.com", "USER");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/logs")
				.header("Authorization", "Bearer " + credencials.getToken())
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	void testSearchNaoAutenticado() throws Exception {

		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/logs")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		MockHttpServletResponse response = mvcResult.getResponse();
		Assertions.assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
	}

}
