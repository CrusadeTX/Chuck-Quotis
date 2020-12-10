package com.project.chuckquotis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.chuckquotis.UserPrincipal;
import com.project.chuckquotis.bean.PostBean;
import com.project.chuckquotis.bean.QuoteBean;
import com.project.chuckquotis.bean.UserBean;
import com.project.chuckquotis.repo.PostRepo;
import com.project.chuckquotis.repo.QuoteRepo;
import com.project.chuckquotis.repo.UserRepo;

@RestController
public class PostController {
private PostRepo postRepo;
private QuoteRepo quoteRepo;
private UserRepo userRepo;
	public PostController(PostRepo postRepo, QuoteRepo quoteRepo, UserRepo userRepo) {
		this.postRepo = postRepo;
		this.quoteRepo = quoteRepo;
		this.userRepo = userRepo;
	}
   @GetMapping(path="/post/all")
	public List<PostBean> getAllPosts(@AuthenticationPrincipal UserPrincipal principal){
		UserBean user = principal.getLoggedInUser();
		if(user!=null) {
		return postRepo.findAll();
		}
		else {
			return null;
		}
		
	}
	@GetMapping(path="/post/personal")
	public List<PostBean> getPersonalPosts(@AuthenticationPrincipal UserPrincipal principal){
		UserBean user = principal.getLoggedInUser();
		if(user!=null) {
		List<PostBean> retrievedPosts = postRepo.findAll();
		List<PostBean> result = new ArrayList<PostBean>();
		for(PostBean post : retrievedPosts) {
			if(post.getUser().getId() == user.getId()) {
				result.add(post);
			}
		}
		return result;
		}
		else {
			return null;
		}
	}
	@PostMapping(path="/post/update")
	public String postQuote(@RequestParam(value="postId")int postId,/*@RequestParam(value="quoteId")int quoteId,*/@RequestParam(value="comment")String comment,@AuthenticationPrincipal UserPrincipal principal ) {
		UserBean user = principal.getLoggedInUser();
		if(user != null) {
			PostBean postBean = postRepo.getOne(postId);
			if(postBean.getUser().getId() == user.getId()) {
			//QuoteBean quote = quoteRepo.getOne(quoteId);
			postBean.setText(comment.trim());
			//postBean.setQuote(quote);
			postBean = postRepo.saveAndFlush(postBean);
			}
			else {
				return "Error: You cant update other people's posts";
			}
			if(postBean != null) {
				return String.valueOf(postBean.getId());
			}
			return "Error: insert failed";
		}
		else {
			return "Error: not logged in";
		}
	}
	@PostMapping(path="/post/add")
	public String addQuote(@RequestParam(value="comment")String comment, @RequestParam(value="quoteId")int quoteId,@AuthenticationPrincipal UserPrincipal principal ) {
		UserBean user = principal.getLoggedInUser();
		if(user != null) {
			if(comment!=null && comment!="" && quoteId!=0) {
			PostBean postBean = new PostBean();
			QuoteBean quote = quoteRepo.getOne(quoteId);
			postBean.setQuote(quote);
			postBean.setText(comment.trim());
			postBean.setUser(user);
	
			postBean = postRepo.saveAndFlush(postBean);
			if(postBean != null) {
				return String.valueOf(postBean.getId());
			}
			return "Error: insert unsuccessful";
		}
			else {
				return "Error: Not all fields were filled!";
			}
		}
		else {
			return "Error: not logged in";
		}
	}
	@DeleteMapping(path="/post/deletebyId")
	public ResponseEntity<Boolean> deletePostById(@RequestParam(value="id")int id,@AuthenticationPrincipal UserPrincipal principal ){
		UserBean user = principal.getLoggedInUser();
		if(user==null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		Optional<PostBean> optionalPost = postRepo.findById(id);
		if(optionalPost.isPresent()) {
			PostBean post = optionalPost.get(); 
			if(post.getUser().getId()==user.getId()) {
				postRepo.delete(post);
			}
			else {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
			}
    		return new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
    		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		
		
	}
		

}
