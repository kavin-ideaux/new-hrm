package com.example.HRM.entity.erecruitment;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hrInterview")
public class HrInterview {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long hrInterviewId;
	  
	  private long candidateId;
	  
	  private String interviewerName;
	  
	  private String interviewerContact;
	  
	  private Date date;
	  
	  private String time;
	  
	  private String feedback;
	  
	  private String candidateQuestions;
	  
	  private boolean started;
	  
	  private boolean inProgress;
	  
	  private boolean completed;
	  
	  private boolean status;
	  
	  private long groupDiscussionId;
	  
	  public long getHrInterviewId() {
	    return this.hrInterviewId;
	  }
	  
	  public void setHrInterviewId(long hrInterviewId) {
	    this.hrInterviewId = hrInterviewId;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	  
	  public String getInterviewerName() {
	    return this.interviewerName;
	  }
	  
	  public void setInterviewerName(String interviewerName) {
	    this.interviewerName = interviewerName;
	  }
	  
	  public String getInterviewerContact() {
	    return this.interviewerContact;
	  }
	  
	  public void setInterviewerContact(String interviewerContact) {
	    this.interviewerContact = interviewerContact;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public String getTime() {
	    return this.time;
	  }
	  
	  public void setTime(String time) {
	    this.time = time;
	  }
	  
	  public String getFeedback() {
	    return this.feedback;
	  }
	  
	  public void setFeedback(String feedback) {
	    this.feedback = feedback;
	  }
	  
	  public String getCandidateQuestions() {
	    return this.candidateQuestions;
	  }
	  
	  public void setCandidateQuestions(String candidateQuestions) {
	    this.candidateQuestions = candidateQuestions;
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
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public long getGroupDiscussionId() {
	    return this.groupDiscussionId;
	  }
	  
	  public void setGroupDiscussionId(long groupDiscussionId) {
	    this.groupDiscussionId = groupDiscussionId;
	  }
	

}
