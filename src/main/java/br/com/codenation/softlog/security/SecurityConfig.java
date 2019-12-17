package br.com.codenation.softlog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;

import br.com.codenation.softlog.security.jwt.JWTAuthorizationFilter;
import br.com.codenation.softlog.security.jwt.SecurityJWT;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private CustomAuthenticationProvider authProvider;
	private SecurityJWT security;

	private static final String[] PUBLIC_MATCHERS = {
			// swagger
			"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**",
			// app login
			"/api/auth/**",
			// create user
			"/api/users/**"
			};

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable();

		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), security));

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}
}