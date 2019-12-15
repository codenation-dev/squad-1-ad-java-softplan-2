package br.com.codenation.softlog.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.codenation.softlog.dto.request.LoginRequestDTO;
import br.com.codenation.softlog.security.jwt.CredencialsJWT;
import br.com.codenation.softlog.security.jwt.SecurityJWT;

@SpringBootTest
@AutoConfigureMockMvc
class AuthResourceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;	

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private SecurityJWT securityJWT;

	@Test
	void testLoginOk() throws Exception {

		LoginRequestDTO loginDTO = new LoginRequestDTO();
		loginDTO.setEmail("admin@codenation.com");
		loginDTO.setPassword("jesus123");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(loginDTO)))
				.andReturn();

		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");
		String token = authorizationHeader.substring("Bearer".length()).trim();

		CredencialsJWT credencials = securityJWT.createCredencials(token);
		Assertions.assertEquals("admin@codenation.com", credencials.getEmail());
	}
	
	@Test
	void testLoginFalha() throws Exception {

		LoginRequestDTO loginDTO = new LoginRequestDTO();
		loginDTO.setEmail("admin@codenation.com");
		loginDTO.setPassword("x");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(loginDTO)))
				.andReturn();

		String authorizationHeader = mvcResult.getResponse().getHeader("Authorization");
		
		Assertions.assertNull(authorizationHeader);
	}

}
