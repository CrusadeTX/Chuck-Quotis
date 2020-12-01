package com.project.chuckquotis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.chuckquotis.bean.QuoteBean;

@Repository
public interface QuoteRepo extends JpaRepository<QuoteBean, Integer> {

}
