package br.com.codenation.softlog.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredencialsJWT {

	private String name;
	private String email;
	private String role;
	private String token;

}
