package com.example.HRM.entity.employee;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "family")
public class FamilyInformations {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long familyInformationsId;
	  
	  private String name;
	  
	  private String relationShip;
	  
	  private Date dob;
	  
	  private Long phone;
	  
	  private Long employeeId;
	  
	  public long getFamilyInformationsId() {
	    return this.familyInformationsId;
	  }
	  
	  public void setFamilyInformationsId(long familyInformationsId) {
	    this.familyInformationsId = familyInformationsId;
	  }
	  
	  public String getName() {
	    return this.name;
	  }
	  
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  public String getRelationShip() {
	    return this.relationShip;
	  }
	  
	  public void setRelationShip(String relationShip) {
	    this.relationShip = relationShip;
	  }
	  
	  public Date getDob() {
	    return this.dob;
	  }
	  
	  public void setDob(Date dob) {
	    this.dob = dob;
	  }
	  
	  public Long getPhone() {
	    return this.phone;
	  }
	  
	  public void setPhone(Long phone) {
	    this.phone = phone;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	}


