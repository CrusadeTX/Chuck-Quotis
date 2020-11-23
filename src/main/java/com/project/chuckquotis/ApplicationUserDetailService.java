package com.project.chuckquotis;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.chuckquotis.bean.RoleBean;
import com.project.chuckquotis.bean.UserBean;
import com.project.chuckquotis.repo.UserRepo;
@Service
public class ApplicationUserDetailService implements UserDetailsService {
	private UserRepo userRepo;
	public ApplicationUserDetailService (UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserBean user = userRepo.findByUsername(username);
		if(username==null) {
			throw new UsernameNotFoundException(username);
		}
		Set<RoleBean> roles = user.getRoles();
		return new UserPrincipal(user,roles);
	}

}
