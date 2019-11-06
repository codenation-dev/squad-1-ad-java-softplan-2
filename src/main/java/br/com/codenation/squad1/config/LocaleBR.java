package br.com.codenation.squad1.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@Configuration
public class LocaleBR {

	@Bean
	public LocaleResolver localeResolver() {
		Locale locale = new Locale("pt", "BR");
		return new FixedLocaleResolver(locale);
	}
}
