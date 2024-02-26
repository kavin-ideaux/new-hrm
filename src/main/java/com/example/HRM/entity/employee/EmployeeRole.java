package com.example.HRM.entity.employee;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeeRole")
public class EmployeeRole {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long employeeRoleId;
	  
	  private Date payrolDate;
	  
	  private String workingHour;
	  
	  private int workingDays;
	  
	  public Long getEmployeeRoleId() {
	    return this.employeeRoleId;
	  }
	  
	  public void setEmployeeRoleId(Long employeeRoleId) {
	    this.employeeRoleId = employeeRoleId;
	  }
	  
	  public Date getPayrolDate() {
	    return this.payrolDate;
	  }
	  
	  public void setPayrolDate(Date payrolDate) {
	    this.payrolDate = payrolDate;
	  }
	  
	  public String getWorkingHour() {
	    return this.workingHour;
	  }
	  
	  public void setWorkingHour(String workingHour) {
	    this.workingHour = workingHour;
	  }
	  
	  public int getWorkingDays() {
	    return this.workingDays;
	  }
	  
	  public void setWorkingDays(int workingDays) {
	    this.workingDays = workingDays;
	  }
	}


