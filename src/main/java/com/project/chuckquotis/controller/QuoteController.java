package com.project.chuckquotis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.chuckquotis.UserPrincipal;
import com.project.chuckquotis.bean.QuoteBean;
import com.project.chuckquotis.bean.UserBean;
import com.project.chuckquotis.repo.QuoteRepo;

@RestController
public class QuoteController {
	private QuoteRepo quoteRepo;

	public QuoteController(QuoteRepo quoteRepo) {
		this.quoteRepo=quoteRepo;
	}
	//@RequestMapping (value = "/quote/edit}", method = RequestMethod.GET)
		
	
	@PostMapping(path="/quote/add")
	public String addQuote(@RequestParam(value="icon")String iconPath, @RequestParam(value="text")String text,@RequestParam(value="saved") boolean isSaved, @RequestParam(value="custom")boolean isCustom,@AuthenticationPrincipal UserPrincipal principal ) {
		UserBean user = principal.getLoggedInUser();
		if(user != null) {
			QuoteBean quoteBean = new QuoteBean();
			quoteBean.setIconPath(iconPath);
			quoteBean.setText(text);
			quoteBean.setSaved(isSaved);
			quoteBean.setUser(user);
			quoteBean.setCustom(isCustom);
			quoteBean = quoteRepo.saveAndFlush(quoteBean);
			if(quoteBean != null) {
				return String.valueOf(quoteBean.getId());
			}
			return "Error: insert unsuccessful";
		}
		else {
			return "Error: not logged in";
		}
	}
	@PostMapping(path="/quote/update")
	public String updateQuote(@RequestParam(value="id")int id,@RequestParam(value="icon")String iconPath, @RequestParam(value="text")String text,@RequestParam(value="saved") boolean isSaved, @RequestParam(value="custom")boolean isCustom,@AuthenticationPrincipal UserPrincipal principal ) {
		
		UserBean user = principal.getLoggedInUser();
		//List<QuoteBean> Quotes = quoteRepo.findAll();
		if(user != null) {
			QuoteBean quoteBean = quoteRepo.getOne(id);
			quoteBean.setIconPath(iconPath);
			quoteBean.setText(text);
			quoteBean.setSaved(isSaved);
			//quoteBean.setUser(user);
			quoteBean.setCustom(isCustom);
			quoteBean = quoteRepo.saveAndFlush(quoteBean);
			if(quoteBean != null) {
				return String.valueOf(quoteBean.getId());
			}
			return "Error: insert unsuccessful";
		}
		else {
			return "Error: not logged in";
		}
	}
	@GetMapping(path="/quote/all")
	public List<QuoteBean> getAllQuotes(){
		return quoteRepo.findAll();
		
	}
	@GetMapping(path="/quote/alluserquotes")
	public List<QuoteBean> getAllUserQuotes( @AuthenticationPrincipal UserPrincipal principal){
		UserBean user = principal.getLoggedInUser();
		List<QuoteBean> retrievedQuotes = quoteRepo.findAll();
		List<QuoteBean> result = new ArrayList<QuoteBean>();
		for(QuoteBean quote : retrievedQuotes) {
			if(quote.getUser().getId() == user.getId()) {
				result.add(quote);
			}
		}
		return result;
		
	}
	
	
	@PostMapping(path="/quote/issaved")
	public ResponseEntity<Boolean> CheckifSaved(@RequestParam(value="text")String text, @AuthenticationPrincipal UserPrincipal principal) {
		UserBean user = principal.getLoggedInUser();
		List<QuoteBean> foundQuotes = quoteRepo.findByText(text);
		for(QuoteBean quote : foundQuotes) {
			if(quote.getUser().getId() == user.getId()) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(false, HttpStatus.OK);
		
		
	}
	@DeleteMapping(path="/quote/delete")
	public ResponseEntity<Boolean> delete(@RequestParam(value="text")String text, @AuthenticationPrincipal UserPrincipal principal) {
		UserBean user = principal.getLoggedInUser();
		List<QuoteBean> foundQuotes = quoteRepo.findByText(text);
		for(QuoteBean quote : foundQuotes) {
			if(quote.getUser().getId() == user.getId()) {
				quoteRepo.delete(quote);
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		
		
	}
	@GetMapping(path="/quote/saved")
	public List<QuoteBean> getSavedQuotes(@AuthenticationPrincipal UserPrincipal principal){
		UserBean user = principal.getLoggedInUser();
		List<QuoteBean> retrievedQuotes = quoteRepo.findAll();
		List<QuoteBean> result = new ArrayList<QuoteBean>();
		for(QuoteBean quote : retrievedQuotes) {
			if(quote.isSaved() &&(quote.getUser().getId() == user.getId())) {
				result.add(quote);
			}
		}
		return result;
	}
	@GetMapping(path="/quote/custom")
	public List<QuoteBean> getCustomQuotes(@AuthenticationPrincipal UserPrincipal principal){
		UserBean user = principal.getLoggedInUser();
		List<QuoteBean> retrievedQuotes = quoteRepo.findAll();
		List<QuoteBean> result = new ArrayList<QuoteBean>();
		for(QuoteBean quote : retrievedQuotes) {
			if(quote.isCustom()&&(quote.getUser().getId() == user.getId())) {
				result.add(quote);
			}
		}
		return result;
	}
	@DeleteMapping(path="/quote/deletebyId")
	public ResponseEntity<Boolean> deleteQuoteById(@RequestParam(value="id")int id,@AuthenticationPrincipal UserPrincipal principal ){
		UserBean user = principal.getLoggedInUser();
		if(user==null) {
			return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
		}
		Optional<QuoteBean> optionalQuote = quoteRepo.findById(id);
		if(optionalQuote.isPresent()) {
			QuoteBean quote = optionalQuote.get(); 
			if(quote.getUser().getId()==user.getId()) {
				quoteRepo.delete(quote);
			}
			else {
			return new ResponseEntity<>(false, HttpStatus.I_AM_A_TEAPOT);
			}
    		return new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
    		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		
		
	}
}


