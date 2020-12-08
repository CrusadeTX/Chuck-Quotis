package com.project.chuckquotis.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.chuckquotis.UserPrincipal;
import com.project.chuckquotis.WebSecurityConfig;
import com.project.chuckquotis.bean.RoleBean;
import com.project.chuckquotis.bean.UserBean;
import com.project.chuckquotis.repo.RoleRepo;
import com.project.chuckquotis.repo.UserRepo;

@RestController
public class UserController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	private UserRepo userRepo;
	private WebSecurityConfig webSecurityConfig;
	private List<UserBean> foundUsers;
	private RoleRepo roleRepo;
	
	
	public UserController(UserRepo userRepo, WebSecurityConfig webSecurityConfig, RoleRepo roleRepo) {
		this.userRepo = userRepo;
		this.webSecurityConfig = webSecurityConfig;
		this.roleRepo = roleRepo;
	}
    @GetMapping(path="/user/current")
    public UserBean getUser(@AuthenticationPrincipal UserPrincipal principal ){
    	UserBean user = principal.getLoggedInUser();
    	if(user!= null) {
    		return user;
    		
    	}
    	else {
    		
    		return null;
    	}
    	
    }
	@PostMapping(path="/user/update")
	
	public List<String> register(@RequestParam(value="id") int id,@RequestParam(value="email")String email, @RequestParam(value="username")String username,
			@RequestParam(value="password")String password, @RequestParam(value="repeatPassword")String repeatPassword, @AuthenticationPrincipal UserPrincipal principal) {
		UserBean loggedUser = principal.getLoggedInUser();
		UserBean user = loggedUser;
		List<String> result = new ArrayList<String>();
		if(loggedUser.getId() == id) {
		boolean usernameExists = false;
		boolean emailExists = false;
		if(password.equals(repeatPassword)) {
			//UserBean user = loggedUser;
			user.setEmail(email);
			user.setPassword(passwordEncoder.encode(password));
			user.setUsername(username);
			//Set<RoleBean> roles = new HashSet<RoleBean>();
			//RoleBean foundRole = roleRepo.findRoleByCode("ROLE_USER");
			//if(foundRole == null) {
				//RoleBean role = new RoleBean();
				//role.setCode("ROLE_USER");
				//roles.add(role);
			//}
			//else {
				//roles.add(foundRole);
			//}
			//user.setRoles(roles);
			foundUsers = userRepo.findAll();
			for(UserBean foundUser : foundUsers) {
				if(foundUser.getUsername().equals(username) && foundUser.getId()!=id) {
					usernameExists = true;
				}
				if(foundUser.getEmail().equals(email) && foundUser.getId()!=id) {
					emailExists = true;
				}
			}
			if(usernameExists || emailExists) {
				if(usernameExists) {
					result.add("Username exists!");
				}
				if(emailExists) {
					result.add("Email exists!");
				}
				return result;
				
			}
			else {
				userRepo.saveAndFlush(user);

				result.add("User has been successfully updated!");
				return result;
			}
			
			//ModelAndView model = new ModelAndView("redirect:/home.html");
		
			
		}
		else {
			result.add("Password mismatch!");
			return result;
		}
		
	
		
	}
		else {
			result.add("Unauthorized!");
			return result;
		}
		
	}

}
