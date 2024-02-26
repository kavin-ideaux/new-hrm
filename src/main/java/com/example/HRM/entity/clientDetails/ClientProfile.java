package com.example.HRM.entity.clientDetails;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Entity
@Table(name = "clientProfile")
public class ClientProfile {
	private static final Logger logger = LogManager.getLogger(com.example.HRM.entity.clientDetails.ClientProfile.class);
	  
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long clientId;
	  
	  private String clientName;
	  
	  private String gender;
	  
	  private String phoneNumber;
	  
	  private String mobileNumber;
	  
	  private String address;
	  
	  @Column(unique = true)
	  private String email;
	  
	  private String city;
	  
	  private String state;
	  
	  private String country;
	  
	  private String password;
	  
	  private String confirmPassword;
	  
	  private String referral;
	  
	  private long roleId;
	  
	  private boolean status;
	  
	  private int zipCode;
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public int getZipCode() {
	    return this.zipCode;
	  }
	  
	  public void setZipCode(int zipCode) {
	    this.zipCode = zipCode;
	  }
	  
	  public String getMobileNumber() {
	    return this.mobileNumber;
	  }
	  
	  public void setMobileNumber(String mobileNumber) {
	    this.mobileNumber = mobileNumber;
	  }
	  
	  public long getRoleId() {
	    return this.roleId;
	  }
	  
	  public void setRoleId(long roleId) {
	    this.roleId = roleId;
	  }
	  
	  public String getConfirmPassword() {
	    return this.confirmPassword;
	  }
	  
	  public void setConfirmPassword(String confirmPassword) {
	    this.confirmPassword = confirmPassword;
	  }
	  
	  public long getClientId() {
	    return this.clientId;
	  }
	  
	  public void setClientId(long clientId) {
	    this.clientId = clientId;
	  }
	  
	  public String getClientName() {
	    return this.clientName;
	  }
	  
	  public void setClientName(String clientName) {
	    this.clientName = clientName;
	  }
	  
	  public String getGender() {
	    return this.gender;
	  }
	  
	  public void setGender(String gender) {
	    this.gender = gender;
	  }
	  
	  public String getPhoneNumber() {
	    return this.phoneNumber;
	  }
	  
	  public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }
	  
	  public String getAddress() {
	    return this.address;
	  }
	  
	  public void setAddress(String address) {
	    this.address = address;
	  }
	  
	  public String getEmail() {
	    return this.email;
	  }
	  
	  public void setEmail(String email) {
	    this.email = email;
	  }
	  
	  public String getCity() {
	    return this.city;
	  }
	  
	  public void setCity(String city) {
	    this.city = city;
	  }
	  
	  public String getState() {
	    return this.state;
	  }
	  
	  public void setState(String state) {
	    this.state = state;
	  }
	  
	  public String getCountry() {
	    return this.country;
	  }
	  
	  public void setCountry(String country) {
	    this.country = country;
	  }
	  
	  public String getPassword() {
	    return this.password;
	  }
	  
	  public void setPassword(String password) {
	    this.password = password;
	    logger.info("Password set: " + password);
	  }
	  
	  public String getReferral() {
	    return this.referral;
	  }
	  
	  public void setReferral(String referral) {
	    this.referral = referral;
	  }
	}


