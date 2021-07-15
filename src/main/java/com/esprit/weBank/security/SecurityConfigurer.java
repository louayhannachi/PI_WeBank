package com.esprit.weBank.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.esprit.weBank.filters.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(appUserDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    auth.authenticationProvider(authProvider);
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl().disable();
		http.requestCache().disable();
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/api/authenticate").permitAll()
		.antMatchers(
				"/api/getAllAccounts",
				"/api/getAllBudget",
				"/api/createAlert/*",
				"/api/createTransaction",
				"/api/convert",
				"/api/createPost",
				"/api/getPostById/*",
				"/api/getAllPosts",
				"/api/deletePost/*",
				"/api/updatePost/*",
				"/api/getNbrPost",
				"/api/getNbrLikes/*",
				"/api/getNbrDislikes/*",
				"/api/getBestPost/*",
				"/api/createReact",
				"/api/getAllReacts",
				"/api/deleteReact/*",
				"/api/createComment",
				"/api/getAllComments",
				"/api/deleteComment/*",
				"/api/getCommentById/*",
				"/api/updateComment/*",
				"/api/report/*",
				"/api/addreclamation",
				"/api/listallreclamations",
				"/api/statInprog",
				"/api/statClosed",
				"/api/statOpen",
				"/api/updatereclamation/*",
				"/api/deletereclamation/*",
				"/api/findbycriteria"
				).hasAnyRole("EMPLOYEE", "CLIENT", "ADMIN")
		.antMatchers(
				"/api/createAdmin",
				"/api/createEmployee",
				"/api/createClient", 
				"/api/updateUser/*" , 
				"/api/deleteUser/*" , 
				"/api/getUserByCin/*", 
				"/api/getAllUsers",
				"/api/createAccount/*",
				"/api/updateAccount/*",
				"/api/updateAccountByRib/*",
				"/api/deleteAccount/*",
				"/api/getAccountByAccNumber/*",
				"/api/getAccountByRib/*"
				).hasAnyRole("EMPLOYEE", "ADMIN")
		.antMatchers(
				"/api/getUserByName/*"
				).hasAnyRole("CLIENT", "ADMIN")
		.anyRequest().authenticated().and()
        .exceptionHandling()
        .authenticationEntryPoint(customAuthenticationEntryPoint)
        .accessDeniedHandler(customAccessDeniedHandler)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);			
	}	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}

}
