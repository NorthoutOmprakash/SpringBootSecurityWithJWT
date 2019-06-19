package com.devglan.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authToken")
public class AuthToken implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String token;
	private String username;
	private Date createdTime;
	private Date expireTime;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private Boolean isExpired = false;

	public AuthToken() {

	}

	public AuthToken(String token, String username) {
		this.token = token;
		this.username = username;
	}

	public AuthToken(String token) {
		this.token = token;

	}

	public AuthToken(Integer id, String token, String username, Date createdTime, Date expireTime, Boolean isExpired) {
		super();
		this.id = id;
		this.token = token;
		this.username = username;
		this.createdTime = createdTime;
		this.expireTime = expireTime;
		this.isExpired = isExpired;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Boolean getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

}
