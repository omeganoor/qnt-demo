//package com.qnt.assigment.demo.config.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Slf4j
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private SecurityFilter authorizationFilter;
//
//	@Value("${allowed.origins}")
//	private String allowedOrigins;
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors()
//				.and().authorizeRequests()
//				.and().csrf().disable()
//				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and().authorizeRequests()
//				.mvcMatchers("/swagger-ui*/**", "/v3/**", "/actuator/**").permitAll()
////				.mvcMatchers(HttpMethod.POST, "/api/v1/**").hasAnyRole("USER", "ADMIN")
////				.mvcMatchers(HttpMethod.PUT, "/api/v1/**").hasAnyRole("USER", "ADMIN")
////				.mvcMatchers(HttpMethod.PATCH, "/api/v1/**").hasAnyRole("USER", "ADMIN")
////				.mvcMatchers(HttpMethod.DELETE, "/api/v1/**").hasAnyRole("USER", "ADMIN")
//				.mvcMatchers("/api/v1/**").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
//	}
//
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		log.info("allowedOrigins: {}", allowedOrigins);
//
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//		configuration.setAllowedHeaders(Collections.singletonList("*"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
//}