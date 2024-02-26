package com.example.HRM.entity.employee;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
public class EmergencyContacts {

	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long emergencyContactsId;
	  
	  private Long employeeId;
	  
	  private String relatinoName;
	  
	  private String address;
	  
	  private Long phoneNumber;
	  
	  private boolean status;
	  
	  public Long getEmergencyContactsId() {
	    return this.emergencyContactsId;
	  }
	  
	  public void setEmergencyContactsId(Long emergencyContactsId) {
	    this.emergencyContactsId = emergencyContactsId;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getRelatinoName() {
	    return this.relatinoName;
	  }
	  
	  public void setRelatinoName(String relatinoName) {
	    this.relatinoName = relatinoName;
	  }
	  
	  public String getAddress() {
	    return this.address;
	  }
	  
	  public void setAddress(String address) {
	    this.address = address;
	  }
	  
	  public Long getPhoneNumber() {
	    return this.phoneNumber;
	  }
	  
	  public void setPhoneNumber(Long phoneNumber) {
	    this.phoneNumber = phoneNumber;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	}
