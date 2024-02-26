package com.example.HRM.entity.organization;
import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long companyId;
	  
	  private String companyName;
	  
	  private String address;
	  
	  private int pincode;
	  
	  private String state;
	  
	  private String country;
	  
	  private Blob profile;
	  
	  private String location;
	  
	  private long phoneNumber1;
	  
	  private long phoneNumber2;
	  
	  private String gstNo;
	  
	  private String taxNo;
	  
	  private String email;
	  
	  private String bankName;
	  
	  private long accountNo;
	  
	  private String ifscCode;
	  
	  private String url;
	  
	  private String branchName;
	  
	  private String holderName;
	  
	  private boolean status;
	  
	  public long getCompanyId() {
	    return this.companyId;
	  }
	  
	  public void setCompanyId(long companyId) {
	    this.companyId = companyId;
	  }
	  
	  public String getCompanyName() {
	    return this.companyName;
	  }
	  
	  public void setCompanyName(String companyName) {
	    this.companyName = companyName;
	  }
	  
	  public String getAddress() {
	    return this.address;
	  }
	  
	  public void setAddress(String address) {
	    this.address = address;
	  }
	  
	  public int getPincode() {
	    return this.pincode;
	  }
	  
	  public void setPincode(int pincode) {
	    this.pincode = pincode;
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
	  
	  public Blob getProfile() {
	    return this.profile;
	  }
	  
	  public void setProfile(Blob profile) {
	    this.profile = profile;
	  }
	  
	  public String getLocation() {
	    return this.location;
	  }
	  
	  public void setLocation(String location) {
	    this.location = location;
	  }
	  
	  public long getPhoneNumber1() {
	    return this.phoneNumber1;
	  }
	  
	  public void setPhoneNumber1(long phoneNumber1) {
	    this.phoneNumber1 = phoneNumber1;
	  }
	  
	  public long getPhoneNumber2() {
	    return this.phoneNumber2;
	  }
	  
	  public void setPhoneNumber2(long phoneNumber2) {
	    this.phoneNumber2 = phoneNumber2;
	  }
	  
	  public String getGstNo() {
	    return this.gstNo;
	  }
	  
	  public void setGstNo(String gstNo) {
	    this.gstNo = gstNo;
	  }
	  
	  public String getTaxNo() {
	    return this.taxNo;
	  }
	  
	  public void setTaxNo(String taxNo) {
	    this.taxNo = taxNo;
	  }
	  
	  public String getEmail() {
	    return this.email;
	  }
	  
	  public void setEmail(String email) {
	    this.email = email;
	  }
	  
	  public String getBankName() {
	    return this.bankName;
	  }
	  
	  public void setBankName(String bankName) {
	    this.bankName = bankName;
	  }
	  
	  public long getAccountNo() {
	    return this.accountNo;
	  }
	  
	  public void setAccountNo(long accountNo) {
	    this.accountNo = accountNo;
	  }
	  
	  public String getIfscCode() {
	    return this.ifscCode;
	  }
	  
	  public void setIfscCode(String ifscCode) {
	    this.ifscCode = ifscCode;
	  }
	  
	  public String getBranchName() {
	    return this.branchName;
	  }
	  
	  public void setBranchName(String branchName) {
	    this.branchName = branchName;
	  }
	  
	  public String getHolderName() {
	    return this.holderName;
	  }
	  
	  public void setHolderName(String holderName) {
	    this.holderName = holderName;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
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
	

}
