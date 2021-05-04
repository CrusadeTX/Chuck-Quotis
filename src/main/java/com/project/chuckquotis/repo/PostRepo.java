package com.project.chuckquotis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.chuckquotis.bean.PostBean;

@Repository
public interface PostRepo extends JpaRepository<PostBean, Integer>{

}
