package com.example.HRM.entity.employee;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank")
public class Bank {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long bankId;
	  
	  private Long employeeId;
	  
	  private String bankName;
	  
	  private String branchName;
	  
	  private String holderName;
	  
	  private String ifseCode;
	  
	  private long accountNumber;
	  
	  private String panNumber;
	  
	  public Long getBankId() {
	    return this.bankId;
	  }
	  
	  public void setBankId(Long bankId) {
	    this.bankId = bankId;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getBankName() {
	    return this.bankName;
	  }
	  
	  public void setBankName(String bankName) {
	    this.bankName = bankName;
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
	  
	  public String getIfseCode() {
	    return this.ifseCode;
	  }
	  
	  public void setIfseCode(String ifseCode) {
	    this.ifseCode = ifseCode;
	  }
	  
	  public long getAccountNumber() {
	    return this.accountNumber;
	  }
	  
	  public void setAccountNumber(long accountNumber) {
	    this.accountNumber = accountNumber;
	  }
	  
	  public String getPanNumber() {
	    return this.panNumber;
	  }
	  
	  public void setPanNumber(String panNumber) {
	    this.panNumber = panNumber;
	  }
	}


