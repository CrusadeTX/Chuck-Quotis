package com.project.chuckquotis.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.project.chuckquotis.WebSecurityConfig;
import com.project.chuckquotis.bean.RoleBean;
import com.project.chuckquotis.bean.UserBean;
import com.project.chuckquotis.repo.RoleRepo;
import com.project.chuckquotis.repo.UserRepo;

@Controller
public class LoginController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	private UserRepo userRepo;
	private WebSecurityConfig webSecurityConfig;
	private List<UserBean> foundUsers;
	private RoleRepo roleRepo;
	
	public LoginController(UserRepo userRepo, WebSecurityConfig webSecurityConfig, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.webSecurityConfig = webSecurityConfig;
		this.roleRepo = roleRepo;
	}
	@PostMapping(path="/register")
	
	public ModelAndView register(@RequestParam(value="email")String email, @RequestParam(value="username")String username,
			@RequestParam(value="password")String password, @RequestParam(value="repeatPassword")String repeatPassword, HttpServletRequest request) {
		 boolean usernameExists = false;
		 boolean emailExists = false;
		if(password.equals(repeatPassword)) {
			UserBean user = new UserBean(username.trim().toLowerCase(),passwordEncoder.encode(password),email.trim().toLowerCase());
			Set<RoleBean> roles = new HashSet<RoleBean>();
			RoleBean foundRole = roleRepo.findRoleByCode("ROLE_USER");
			if(foundRole == null) {
				RoleBean role = new RoleBean();
				role.setCode("ROLE_USER");
				roles.add(role);
			}
			else {
				roles.add(foundRole);
			}
			user.setRoles(roles);
			foundUsers = userRepo.findAll();
			for(UserBean foundUser : foundUsers) {
				if(foundUser.getUsername().equals(username.trim().toLowerCase())) {
					usernameExists = true;
				}
				if(foundUser.getEmail().equals(email.trim().toLowerCase())) {
					emailExists = true;
				}
			}
			if(usernameExists || emailExists) {
				ModelAndView model = new ModelAndView();
				model.addObject("usernameExists", usernameExists);
				model.addObject("emailExists",emailExists);
				model.setViewName("register.html");
				return model;
			}
			else {
				userRepo.saveAndFlush(user);
				ModelAndView model = new ModelAndView("redirect:/login");
				model.addObject("user",user);
				return model;
			}
			
			//ModelAndView model = new ModelAndView("redirect:/home.html");
		
			
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
	@GetMapping(path="/register")
	public ModelAndView register(ModelAndView model) {
		if (model==null) {
		model = new ModelAndView();
		}
		model.setViewName("register.html");
		return model;
		
	}
	@GetMapping(path="/home")
	public ModelAndView home(ModelAndView model) {
		if (model==null) {
		model = new ModelAndView();
		}
		model.setViewName("home.html");
		return model;
		
	}

}
