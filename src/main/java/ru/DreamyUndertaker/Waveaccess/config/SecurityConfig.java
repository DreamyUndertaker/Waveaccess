package ru.DreamyUndertaker.Waveaccess.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.DreamyUndertaker.Waveaccess.services.PersonDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final PersonDetailsService personDetailsService;
	@Autowired
	public SecurityConfig(PersonDetailsService personDetailsService) {
		this.personDetailsService = personDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeRequests ((authorizeRequests) -> authorizeRequests
						.requestMatchers("/registration").permitAll()
						.requestMatchers("/", "/home").hasAnyRole("USER", "ADMIN")
						.requestMatchers("/admin").hasRole("ADMIN")
				)
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/perform-login")
						.defaultSuccessUrl("/home")
						.permitAll()
				)
				.logout((logout -> logout
						.logoutSuccessUrl("/login")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				));
		;
		return http.build();
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(personDetailsService);
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
