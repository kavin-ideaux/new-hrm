package com.example.HRM.entity.erecruitment;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taskAssigned")
public class TaskAssigned {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long taskId;
	  
	  private String taskAssignee;
	  
	  private Date date;
	  
	  private String startTime;
	  
	  private String endTime;
	  
	  private String Comment;
	  
	  private String taskPriority;
	  
	  private String progressType;
	  
	  private boolean started;
	  
	  private boolean completed;
	  
	  private long interviewProcessId;
	  
	  private long candidateId;
	  
	  private String approvalType;
	  
	  private boolean status;
	  
	  private Blob taskFile;
	  
	  private String taskUrl;
	  
	  public String getApprovalType() {
	    return this.approvalType;
	  }
	  
	  public void setApprovalType(String approvalType) {
	    this.approvalType = approvalType;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public String getProgressType() {
	    return this.progressType;
	  }
	  
	  public void setProgressType(String progressType) {
	    this.progressType = progressType;
	  }
	  
	  public long getTaskId() {
	    return this.taskId;
	  }
	  
	  public void setTaskId(long taskId) {
	    this.taskId = taskId;
	  }
	  
	  public String getTaskAssignee() {
	    return this.taskAssignee;
	  }
	  
	  public void setTaskAssignee(String taskAssignee) {
	    this.taskAssignee = taskAssignee;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public String getStartTime() {
	    return this.startTime;
	  }
	  
	  public void setStartTime(String startTime) {
	    this.startTime = startTime;
	  }
	  
	  public String getEndTime() {
	    return this.endTime;
	  }
	  
	  public void setEndTime(String endTime) {
	    this.endTime = endTime;
	  }
	  
	  public String getComment() {
	    return this.Comment;
	  }
	  
	  public void setComment(String comment) {
	    this.Comment = comment;
	  }
	  
	  public String getTaskPriority() {
	    return this.taskPriority;
	  }
	  
	  public void setTaskPriority(String taskPriority) {
	    this.taskPriority = taskPriority;
	  }
	  
	  public boolean isStarted() {
	    return this.started;
	  }
	  
	  public void setStarted(boolean started) {
	    this.started = started;
	  }
	  
	  public boolean isCompleted() {
	    return this.completed;
	  }
	  
	  public void setCompleted(boolean completed) {
	    this.completed = completed;
	  }
	  
	  public long getInterviewProcessId() {
	    return this.interviewProcessId;
	  }
	  
	  public void setInterviewProcessId(long interviewProcessId) {
	    this.interviewProcessId = interviewProcessId;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	  
	  public Blob getTaskFile() {
	    return this.taskFile;
	  }
	  
	  public void setTaskFile(Blob taskFile) {
	    this.taskFile = taskFile;
	  }
	  
	  public String getTaskUrl() {
	    return this.taskUrl;
	  }
	  
	  public void setTaskUrl(String taskUrl) {
	    this.taskUrl = taskUrl;
	  }
	

}
