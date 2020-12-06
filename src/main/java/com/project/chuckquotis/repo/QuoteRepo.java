package com.project.chuckquotis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.chuckquotis.bean.QuoteBean;
import com.project.chuckquotis.bean.UserBean;

@Repository
public interface QuoteRepo extends JpaRepository<QuoteBean, Integer> {
	
	public List<QuoteBean> findByText(String text);
	public List<QuoteBean> findByTextAndUser(String text,UserBean user);

}
