package com.example.HRM.entity.erecruitment;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interviewProcess")
public class InterviewProcess {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long interviewProcessId;
	  
	  private String jobPosition;
	  
	  private String interviewType;
	  
	  private String feedback;
	  
	  private Date startDate;
	  
	  private Date endDate;
	  
	  private boolean started;
	  
	  private boolean inProgress;
	  
	  private boolean completed;
	  
	  private long candidateId;
	  
	  private boolean status;
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public long getInterviewProcessId() {
	    return this.interviewProcessId;
	  }
	  
	  public void setInterviewProcessId(long interviewProcessId) {
	    this.interviewProcessId = interviewProcessId;
	  }
	  
	  public String getJobPosition() {
	    return this.jobPosition;
	  }
	  
	  public void setJobPosition(String jobPosition) {
	    this.jobPosition = jobPosition;
	  }
	  
	  public String getInterviewType() {
	    return this.interviewType;
	  }
	  
	  public void setInterviewType(String interviewType) {
	    this.interviewType = interviewType;
	  }
	  
	  public String getFeedback() {
	    return this.feedback;
	  }
	  
	  public void setFeedback(String feedback) {
	    this.feedback = feedback;
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
	  
	  public boolean isStarted() {
	    return this.started;
	  }
	  
	  public void setStarted(boolean started) {
	    this.started = started;
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
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	

}
