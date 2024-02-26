package com.example.HRM.entity.project;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long taskId;
	  
	  private long projectId;
	  
	  private String taskName;
	  
	  private String ticketNumber;
	  
	  private String description;
	  
	  private Date startDate;
	  
	  private Date endDate;
	  
	  private long employeeId;
	  
	  private String taskProcess;
	  
	  private boolean todo;
	  
	  private boolean inProgress;
	  
	  private boolean completed;
	  
	  private boolean moved;
	  
	  private Date completedDate;
	  
	  private String priority;
	  
	  public String getPriority() {
	    return this.priority;
	  }
	  
	  public void setPriority(String priority) {
	    this.priority = priority;
	  }
	  
	  public Date getCompletedDate() {
	    return this.completedDate;
	  }
	  
	  public void setCompletedDate(Date completedDate) {
	    this.completedDate = completedDate;
	  }
	  
	  public long getTaskId() {
	    return this.taskId;
	  }
	  
	  public void setTaskId(long taskId) {
	    this.taskId = taskId;
	  }
	  
	  public long getProjectId() {
	    return this.projectId;
	  }
	  
	  public void setProjectId(long projectId) {
	    this.projectId = projectId;
	  }
	  
	  public String getTaskName() {
	    return this.taskName;
	  }
	  
	  public void setTaskName(String taskName) {
	    this.taskName = taskName;
	  }
	  
	  public String getTicketNumber() {
	    return this.ticketNumber;
	  }
	  
	  public void setTicketNumber(String ticketNumber) {
	    this.ticketNumber = ticketNumber;
	  }
	  
	  public String getDescription() {
	    return this.description;
	  }
	  
	  public void setDescription(String description) {
	    this.description = description;
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
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public String getTaskProcess() {
	    return this.taskProcess;
	  }
	  
	  public void setTaskProcess(String taskProcess) {
	    this.taskProcess = taskProcess;
	  }
	  
	  public boolean isTodo() {
	    return this.todo;
	  }
	  
	  public void setTodo(boolean todo) {
	    this.todo = todo;
	  }
	  
	  public boolean isInProgress() {
	    return this.inProgress;
	  }
	  
	  public void setInProgress(boolean inProgress) {
	    this.inProgress = inProgress;
	  }
	  
	  public boolean isCompleted() {
	    return this.completed;
	  }
	  
	  public void setCompleted(boolean completed) {
	    this.completed = completed;
	  }
	  
	  public boolean isMoved() {
	    return this.moved;
	  }
	  
	  public void setMoved(boolean moved) {
	    this.moved = moved;
	  }
	

}
