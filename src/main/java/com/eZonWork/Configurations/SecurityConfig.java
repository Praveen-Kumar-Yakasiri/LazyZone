package com.eZonWork.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eZonWork.JwtFilter.JwtFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter filter;
	
	
	
	
 
	@Bean
	public UserDetailsService detailsService() {
		return new UserDetailsServiceInfo();
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();	
		provider.setUserDetailsService(detailsService());
		provider.setPasswordEncoder(encoder());
		return provider;
	}
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity security) throws Exception {
		return security.csrf(csrf->csrf.disable()).cors(cors->cors.disable())
				.authorizeHttpRequests(auth->auth.requestMatchers("/log/loginUser","/common/loginAsAdmin","/common/saveOrUpdateUser").permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.disable())
				.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())			
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
