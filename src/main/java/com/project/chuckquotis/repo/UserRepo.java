package com.project.chuckquotis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.chuckquotis.bean.UserBean;

public interface UserRepo extends JpaRepository<UserBean, Integer> {
	
	UserBean findUserByUsernameAndPassword(String username, String password);
	UserBean findByUsername(String username);

}
