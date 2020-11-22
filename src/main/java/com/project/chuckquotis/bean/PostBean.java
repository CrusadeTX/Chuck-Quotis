package com.project.chuckquotis.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="posts")
public class PostBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(name="text", nullable = true, length=256)
	private String text;
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserBean user;
	@ManyToOne
	@JoinColumn(name="quote_id")
	private QuoteBean quote;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public QuoteBean getQuote() {
		return quote;
	}
	public void setQuote(QuoteBean quote) {
		this.quote = quote;
	}
	

}
