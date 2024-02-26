package com.example.HRM.entity.employee;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resignations")
public class Resignations {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long resignationsId;
	  
	  private String type;
	  
	  @Column(length = 4000)
	  private String reason;
	  
	  private Date resignationsDate;
	  
	  private long employeeId;
	  
	  private boolean status;
	  
	  public String getType() {
	    return this.type;
	  }
	  
	  public void setType(String type) {
	    this.type = type;
	  }
	  
	  public long getResignationsId() {
	    return this.resignationsId;
	  }
	  
	  public void setResignationsId(long resignationsId) {
	    this.resignationsId = resignationsId;
	  }
	  
	  public String getReason() {
	    return this.reason;
	  }
	  
	  public void setReason(String reason) {
	    this.reason = reason;
	  }
	  
	  public Date getResignationsDate() {
	    return this.resignationsDate;
	  }
	  
	  public void setResignationsDate(Date resignationsDate) {
	    this.resignationsDate = resignationsDate;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
