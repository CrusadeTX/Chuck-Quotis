package com.project.chuckquotis.bean;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class QuotesUsersBean {
	@EmbeddedId
	QuotesUsersKey id;
	@ManyToOne
	@MapsId("quote_id")
	@JoinColumn(name="quote_id")
	QuoteBean quote;
	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name="user_id")
	UserBean user;
	public QuotesUsersKey getId() {
		return id;
	}
	public void setId(QuotesUsersKey id) {
		this.id = id;
	}
	public QuoteBean getQuote() {
		return quote;
	}
	public void setQuote(QuoteBean quote) {
		this.quote = quote;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	
}
