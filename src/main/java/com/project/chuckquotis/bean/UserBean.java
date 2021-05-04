package com.project.chuckquotis.bean;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user")
@JsonIgnoreProperties({"quotes","posts","roles"})
public class UserBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="username", nullable=false, unique=true, length = 40)
	private String username;
	@Column(name="password", nullable=false, length=60)
	private String password;
	@Column(name="email", nullable=false, unique=true, length=256)
	private String email;
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<PostBean> posts;
   // @ManyToMany( fetch = FetchType.EAGER)
   // @JoinTable(name="user_quote", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="quote_id"))
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<QuoteBean> quotes;
    @ManyToMany( fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="account_role",
    		joinColumns = @JoinColumn(name="account_id"), 
    		inverseJoinColumns = @JoinColumn(name="role_id")
    		)
    private Set<RoleBean> roles;
 
    public UserBean() {};
    public UserBean(String username, String email) {
    	this.username = username;
    	this.email = email;
    	
    };
    public UserBean(String username, String password, String email ) {
    	this.username = username;
    	this.password = password;
    	this.email = email;
    	
    };
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<PostBean> getPosts() {
		return posts;
	}
	public void setPosts(List<PostBean> posts) {
		this.posts = posts;
	}
	public List<QuoteBean> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<QuoteBean> quotes) {
		this.quotes = quotes;
	}
	public Set<RoleBean> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleBean> roles) {
		this.roles = roles;
	}
	
    

}
