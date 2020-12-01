package com.project.chuckquotis;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private ApplicationUserDetailService userDetailService; 
	
	public WebSecurityConfig(ApplicationUserDetailService userDetailService) {

		this.userDetailService = userDetailService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		super.configure(auth); 
		auth.userDetailsService(userDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.authorizeRequests().antMatchers("/**").permitAll()
		.requestMatchers(EndpointRequest.toAnyEndpoint())
		.permitAll()
		.and().csrf().disable();
		http.headers().frameOptions().disable();*/
		
		 http
	        .authorizeRequests()
	          .antMatchers("/public/**", "/resources/**","/resources/public/**")
	            .permitAll().anyRequest().authenticated().and()
	       .formLogin()
	         .loginPage("/login")
	         .permitAll()
	         .and()
	      .logout() 
	        .permitAll().and().csrf().disable();
		 
		}
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/api/auth/**","/v2/api-docs", 
	            "/configuration/ui", 
	            "/swagger-resources", 
	            "/configuration/security",
	            "/swagger-ui.html", 
	            "/webjars/**",
	            "/favicon.ico",
	            "/**/*.png",
	            "/**/*.gif",
	            "/**/*.svg",
	            "/**/*.jpg",
	            "/**/*.css",
	            "/**/*.js");
	}
		

}
