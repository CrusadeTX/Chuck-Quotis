package com.project.chuckquotis.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="quotes")
public class QuoteBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="icon",nullable=false, length=70)
	private String iconPath;
	@Column(name="text", nullable=false, length=256)
	private String text;
	@OneToMany(mappedBy="quotes", fetch = FetchType.EAGER)
	private List<PostBean> posts;
    @ManyToMany(mappedBy="quotes", fetch = FetchType.EAGER)
	private List<UserBean> users;
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
	public List<UserBean> getUsers() {
		return users;
	}
	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

}
