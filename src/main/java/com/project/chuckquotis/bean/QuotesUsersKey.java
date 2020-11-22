package com.project.chuckquotis.bean;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class QuotesUsersKey {
@Column(name = "user_id")
int userId;	 
@Column(name = "quote_id")
int quoteId;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getQuoteId() {
	return quoteId;
}
public void setQuoteId(int quoteId) {
	this.quoteId = quoteId;
}

}
