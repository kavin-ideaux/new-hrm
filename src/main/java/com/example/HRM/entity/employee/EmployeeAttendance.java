package com.example.HRM.entity.employee;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employeeAtt")
public class EmployeeAttendance {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long employeeAttId;
	  
	  private Long employeeId;
	  
	  private boolean punchIn;
	  
	  private boolean punchOut;
	  
	  private String InTime;
	  
	  private String OutTime;
	  
	  private LocalDate inDate;
	  
	  private String workingHour;
	  
	  private String attendance;
	  
	  public String getAttendance() {
	    return this.attendance;
	  }
	  
	  public void setAttendance(String attendance) {
	    this.attendance = attendance;
	  }
	  
	  public Long getEmployeeAttId() {
	    return this.employeeAttId;
	  }
	  
	  public void setEmployeeAttId(Long employeeAttId) {
	    this.employeeAttId = employeeAttId;
	  }
	  
	  public Long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(Long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public boolean isPunchIn() {
	    return this.punchIn;
	  }
	  
	  public void setPunchIn(boolean punchIn) {
	    this.punchIn = punchIn;
	  }
	  
	  public boolean isPunchOut() {
	    return this.punchOut;
	  }
	  
	  public void setPunchOut(boolean punchOut) {
	    this.punchOut = punchOut;
	  }
	  
	  public String getInTime() {
	    return this.InTime;
	  }
	  
	  public void setInTime(String inTime) {
	    this.InTime = inTime;
	  }
	  
	  public String getOutTime() {
	    return this.OutTime;
	  }
	  
	  public void setOutTime(String outTime) {
	    this.OutTime = outTime;
	  }
	  
	  public LocalDate getInDate() {
	    return this.inDate;
	  }
	  
	  public void setInDate(LocalDate inDate) {
	    this.inDate = inDate;
	  }
	  
	  public String getWorkingHour() {
	    return this.workingHour;
	  }
	  
	  public void setWorkingHour(String workingHour) {
	    this.workingHour = workingHour;
	  }
	}


