package br.com.codenation.softlog.security.jwt;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private SecurityJWT securityJWT;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, SecurityJWT security) {
		super(authenticationManager);
		this.securityJWT = security;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (!StringUtils.isEmpty(authorizationHeader)) {
			String token = authorizationHeader.substring("Bearer".length()).trim();

			CredencialsJWT credencials = securityJWT.createCredencials(token);

			if (credencials != null) {
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(credencials, null,
						Arrays.asList(new SimpleGrantedAuthority(credencials.getRole())));

				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}

		chain.doFilter(request, response);
	}
}