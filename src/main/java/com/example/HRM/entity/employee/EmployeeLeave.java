package com.example.HRM.entity.employee;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeeleave")
public class EmployeeLeave {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long employeeLeaveId;
	  
	  private Long employeeId;
	  
	  private boolean approvedBy;
	  
	  private Date date;
	  
	  private Date toDate;
	  
	  private String reason;
	  
	  private int totalDay;
	  
	  private boolean status;
	  
	  public Long getEmployeeLeaveId() {
	    return this.employeeLeaveId;
	  }
	  
	  public void setEmployeeLeaveId(Long employeeLeaveId) {
	    this.employeeLeaveId = employeeLeaveId;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public boolean isApprovedBy() {
	    return this.approvedBy;
	  }
	  
	  public void setApprovedBy(boolean approvedBy) {
	    this.approvedBy = approvedBy;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public Date getToDate() {
	    return this.toDate;
	  }
	  
	  public void setToDate(Date toDate) {
	    this.toDate = toDate;
	  }
	  
	  public String getReason() {
	    return this.reason;
	  }
	  
	  public void setReason(String reason) {
	    this.reason = reason;
	  }
	  
	  public int getTotalDay() {
	    return this.totalDay;
	  }
	  
	  public void setTotalDay(int totalDay) {
	    this.totalDay = totalDay;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
