package com.example.HRM.entity.project;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_assigned")
public class ProjectAssigned {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long projectAssignedId;
	  
	  private long projectId;
	  
	  private String projectNumber;
	  
	  private Date startDate;
	  
	  private Date endDate;
	  
	  private String process;
	  
	  private boolean pending;
	  
	  private boolean completed;
	  
	  private boolean hold;
	  
	  private boolean onProcess;
	  
	  private boolean started;
	  
	  private Date completedDate;
	  
	  private long departmentId;
	  
	  public Date getCompletedDate() {
	    return this.completedDate;
	  }
	  
	  public void setCompletedDate(Date completedDate) {
	    this.completedDate = completedDate;
	  }
	  
	  public boolean isStarted() {
	    return this.started;
	  }
	  
	  public void setStarted(boolean started) {
	    this.started = started;
	  }
	  
	  public long getDepartmentId() {
	    return this.departmentId;
	  }
	  
	  public void setDepartmentId(long departmentId) {
	    this.departmentId = departmentId;
	  }
	  
	  public long getProjectAssignedId() {
	    return this.projectAssignedId;
	  }
	  
	  public void setProjectAssignedId(long projectAssignedId) {
	    this.projectAssignedId = projectAssignedId;
	  }
	  
	  public long getProjectId() {
	    return this.projectId;
	  }
	  
	  public void setProjectId(long projectId) {
	    this.projectId = projectId;
	  }
	  
	  public String getProjectNumber() {
	    return this.projectNumber;
	  }
	  
	  public void setProjectNumber(String projectNumber) {
	    this.projectNumber = projectNumber;
	  }
	  
	  public Date getStartDate() {
	    return this.startDate;
	  }
	  
	  public void setStartDate(Date startDate) {
	    this.startDate = startDate;
	  }
	  
	  public Date getEndDate() {
	    return this.endDate;
	  }
	  
	  public void setEndDate(Date endDate) {
	    this.endDate = endDate;
	  }
	  
	  public String getProcess() {
	    return this.process;
	  }
	  
	  public void setProcess(String process) {
	    this.process = process;
	  }
	  
	  public boolean isPending() {
	    return this.pending;
	  }
	  
	  public void setPending(boolean pending) {
	    this.pending = pending;
	  }
	  
	  public boolean isCompleted() {
	    return this.completed;
	  }
	  
	  public void setCompleted(boolean completed) {
	    this.completed = completed;
	  }
	  
	  public boolean isHold() {
	    return this.hold;
	  }
	  
	  public void setHold(boolean hold) {
	    this.hold = hold;
	  }
	  
	  public boolean isOnProcess() {
	    return this.onProcess;
	  }
	  
	  public void setOnProcess(boolean onProcess) {
	    this.onProcess = onProcess;
	  }
	

}
