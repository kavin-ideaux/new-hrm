package com.example.HRM.entity.project;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issue")
public class Issue {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long issueId;
	  
	  private long projectId;
	  
	  private long employeeId;
	  
	  private long issueTypeId;
	  
	  private long taskId;
	  
	  private LocalDateTime date;
	  
	  private boolean status;
	  
	  public long getTaskId() {
	    return this.taskId;
	  }
	  
	  public void setTaskId(long taskId) {
	    this.taskId = taskId;
	  }
	  
	  public long getIssueTypeId() {
	    return this.issueTypeId;
	  }
	  
	  public void setIssueTypeId(long issueTypeId) {
	    this.issueTypeId = issueTypeId;
	  }
	  
	  public long getIssueId() {
	    return this.issueId;
	  }
	  
	  public void setIssueId(long issueId) {
	    this.issueId = issueId;
	  }
	  
	  public long getProjectId() {
	    return this.projectId;
	  }
	  
	  public void setProjectId(long projectId) {
	    this.projectId = projectId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public LocalDateTime getDate() {
	    return this.date;
	  }
	  
	  public void setDate(LocalDateTime date) {
	    this.date = date;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
