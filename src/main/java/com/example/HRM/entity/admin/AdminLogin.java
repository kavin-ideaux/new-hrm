package com.example.HRM.entity.admin;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin_login")
public class AdminLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;

	private String password;

	private Blob image;

	private String name;

	private String confirmPassword;

	private long roleId;
	private boolean status;
	private String roleType;

	private String url;
	@Column(name = "intime", columnDefinition = "VARCHAR(12)")
	private String intime = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
	private LocalDate date = LocalDate.now();
	
	
	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Blob getImage() {
		return this.image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
