package br.com.codenation.softlog.security.jwt;

import static java.util.logging.Logger.getLogger;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SecurityJWT {

	private static final Logger LOG = getLogger(SecurityJWT.class.getName());

	@Value("${jwt.privateKey}")
	private String privateKey;

	@Value("${jwt.publicKey}")
	private String publicKey;

	@Value("${jwt.expiration}")
	private Long expiration;

	public CredencialsJWT createCredencials(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(KeyUtil.getPublicKey(this.publicKey))
					.parseClaimsJws(token);

			return new CredencialsJWT((String) claims.getBody().get("name"), (String) claims.getBody().get("email"),
					(String) claims.getBody().get("role"), token);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);

			return null;
		}
	}

	public CredencialsJWT createCredencials(String nome, String email, String perfil) {
		HashMap<String, Object> claims = new HashMap<>();

		claims.put("email", email);
		claims.put("name", nome);
		claims.put("role", perfil);

		try {
			String token = Jwts.builder().setClaims(claims)
					.signWith(SignatureAlgorithm.RS256, KeyUtil.getPrivateKey(this.privateKey))
					.setExpiration(new Date(System.currentTimeMillis() + this.expiration))
					.setIssuedAt(new Date(System.currentTimeMillis())).compact();

			return new CredencialsJWT(nome, email, perfil, token);
		} catch (Exception e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);

			return null;
		}
	}

	public CredencialsJWT getLoggedUser() {
		try {
			return (CredencialsJWT) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			throw new RuntimeException("usuario-nao-autenticado");
		}
	}

}