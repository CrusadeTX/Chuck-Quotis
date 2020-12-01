package com.project.chuckquotis.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.project.chuckquotis.WebSecurityConfig;
import com.project.chuckquotis.bean.UserBean;
import com.project.chuckquotis.repo.UserRepo;

@Controller
public class LoginController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	private UserRepo userRepo;
	private WebSecurityConfig webSecurityConfig;
	public LoginController(UserRepo userRepo, WebSecurityConfig webSecurityConfig) {
		this.userRepo = userRepo;
		this.webSecurityConfig = webSecurityConfig;
	}
	@PostMapping(path="/register")
	public ModelAndView register(@RequestParam(value="email")String email, @RequestParam(value="username")String username,
			@RequestParam(value="password")String password, @RequestParam(value="repeatPassword")String repeatPassword) {
		if(password.equals(repeatPassword)) {
			UserBean user = new UserBean(username,passwordEncoder.encode(password),email);
			userRepo.saveAndFlush(user);
			ModelAndView model = new ModelAndView("redirect:/home.html");
			model.setViewName("home.html");
			model.addObject("user",user);
			return model;
			
		}
		return null;
	
		
	}
	/*@PostMapping(path="/login")
	public String login(@RequestParam(value="username")String username, 
			@RequestParam(value="password")String password, 
			HttpSession session) {
		UserBean user = userRepo.findUserByUsernameAndPassword(username,password);
		if(user != null) {
			session.setAttribute("user", user);
			try {
				UserDetails userDetails = webSecurityConfig.userDetailsServiceBean().loadUserByUsername(user.getUsername());
				if(userDetails!=null) {
					Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
					
					SecurityContextHolder.getContext().setAuthentication(auth);
					ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes(); 
					HttpSession http = attr.getRequest().getSession(true);
					http.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
					
					
				}
				return "home.html";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
			return "login.html";
		
		
	
	}*/
	@GetMapping(path="/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login.html");
		return model;
		
	}
	@GetMapping(path="/home")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName("home.html");
		return model;
		
	}

}
