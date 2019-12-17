package br.com.codenation.softlog.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.codenation.softlog.dto.request.LoginRequestDTO;
import br.com.codenation.softlog.security.CustomAuthenticationProvider;
import br.com.codenation.softlog.security.jwt.SecurityJWT;
import br.com.codenation.softlog.service.AuthService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthResource.class)
class AuthResourceTest {

	@Autowired
	private MockMvc mockMvc;	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private AuthService authService;
	
	@MockBean
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@MockBean
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
		
		Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
	}
	
	
	@Test
	void testLoginSemEmail() throws Exception {
		
		LoginRequestDTO loginDTO = new LoginRequestDTO();
		loginDTO.setPassword("jesus123");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(loginDTO)))
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), mvcResult.getResponse().getStatus());
	}
	
	@Test
	void testLoginSemSenha() throws Exception {
		
		LoginRequestDTO loginDTO = new LoginRequestDTO();
		loginDTO.setEmail("admin@codenation.com");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(loginDTO)))
				.andReturn();
		
		Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY.value(), mvcResult.getResponse().getStatus());
	}

}
