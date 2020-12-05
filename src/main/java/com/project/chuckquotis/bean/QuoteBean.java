package com.project.chuckquotis.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="quote")
@JsonIgnoreProperties({"posts"})
public class QuoteBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="icon",nullable=false, length=70)
	private String iconPath;
	@Column(name="text", nullable=false, length=256)
	private String text;
	@Column(name="saved", nullable=false)
	private boolean isSaved;
	@OneToMany(mappedBy="quote", fetch = FetchType.EAGER)
	private List<PostBean> posts;
    @ManyToOne
    @JoinColumn(name="user_id")
	private UserBean user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<PostBean> getPosts() {
		return posts;
	}
	public void setPosts(List<PostBean> posts) {
		this.posts = posts;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public boolean isSaved() {
		return isSaved;
	}
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}
	
	

}
