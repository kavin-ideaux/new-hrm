package com.example.HRM.entity.employee;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personal")
public class Personal {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long personalId;
	  
	  private String passportNo;
	  
	  private Date passportExpDate;
	  
	  private String nationality;
	  
	  private String married;
	  
	  private String religion;
	  
	  private long tel;
	  
	  private long noOfChildren;
	  
	  private long employeeId;
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public Long getPersonalId() {
	    return this.personalId;
	  }
	  
	  public void setPersonalId(Long personalId) {
	    this.personalId = personalId;
	  }
	  
	  public String getPassportNo() {
	    return this.passportNo;
	  }
	  
	  public void setPassportNo(String passportNo) {
	    this.passportNo = passportNo;
	  }
	  
	  public Date getPassportExpDate() {
	    return this.passportExpDate;
	  }
	  
	  public void setPassportExpDate(Date passportExpDate) {
	    this.passportExpDate = passportExpDate;
	  }
	  
	  public String getNationality() {
	    return this.nationality;
	  }
	  
	  public void setNationality(String nationality) {
	    this.nationality = nationality;
	  }
	  
	  public String getMarried() {
	    return this.married;
	  }
	  
	  public void setMarried(String married) {
	    this.married = married;
	  }
	  
	  public String getReligion() {
	    return this.religion;
	  }
	  
	  public void setReligion(String religion) {
	    this.religion = religion;
	  }
	  
	  public long getTel() {
	    return this.tel;
	  }
	  
	  public void setTel(long tel) {
	    this.tel = tel;
	  }
	  
	  public long getNoOfChildren() {
	    return this.noOfChildren;
	  }
	  
	  public void setNoOfChildren(long noOfChildren) {
	    this.noOfChildren = noOfChildren;
	  }
	}


