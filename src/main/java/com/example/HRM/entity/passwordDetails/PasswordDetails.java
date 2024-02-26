package com.example.HRM.entity.passwordDetails;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "details")
public class PasswordDetails {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long id;
	  
	  private String username;
	  
	  private String password;
	  
	  public long getId() {
	    return this.id;
	  }
	  
	  public void setId(long id) {
	    this.id = id;
	  }
	  
	  public String getUsername() {
	    return this.username;
	  }
	  
	  public void setUsername(String username) {
	    this.username = username;
	  }
	  
	  public String getPassword() {
	    return this.password;
	  }
	  
	  public void setPassword(String password) {
	    this.password = password;
	  }
	}


