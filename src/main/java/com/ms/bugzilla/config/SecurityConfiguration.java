/*
 * package com.ms.bugzilla.config;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter;
 * 
 * 
 * 
 * 
 * @Configuration public class SecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity security) throws Exception{
 * security.authorizeRequests().antMatchers("/").permitAll().and().
 * authorizeRequests().antMatchers("/h2/**").permitAll();
 * security.csrf().disable(); security.headers().frameOptions().disable(); }
 * 
 * }
 */