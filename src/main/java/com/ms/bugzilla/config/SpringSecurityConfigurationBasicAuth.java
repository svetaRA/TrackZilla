
package com.ms.bugzilla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ms.bugzilla.jwt.JwtTokenAuthorizationOncePerRequestFilter;
import com.ms.bugzilla.jwt.JwtUnAuthorizedResponseAuthenticationEntryPoint;
import com.ms.bugzilla.service.CustomUserDetailsService;

@Configuration

@EnableWebSecurity

@Order
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUnAuthorizedResponseAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtTokenAuthorizationOncePerRequestFilter jwtAuthenticationFilter;

	/*
	 * @Bean public JwtTokenAuthorizationOncePerRequestFilter
	 * jwtAuthenticationFilter() { return new
	 * JwtTokenAuthorizationOncePerRequestFilter(); }
	 */
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	AuthenticationManager authenticationManagerBean;
	
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http .cors().and() .csrf().disable() .authorizeRequests()
		 * .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
		 * .anyRequest().authenticated() ;//.formLogin().and() .httpBasic();
		 */

		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/", "/favicon.ico", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.jpg", "/**/*.html",
						"/**/*.css", "/**/*.js").
				permitAll().antMatchers(HttpMethod.POST, "/auth/signup/**").
				permitAll().antMatchers(HttpMethod.POST, "/auth/signin/**")
				.permitAll().antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
				.permitAll().anyRequest().authenticated();

// Add our custom JWT security filter
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

}
