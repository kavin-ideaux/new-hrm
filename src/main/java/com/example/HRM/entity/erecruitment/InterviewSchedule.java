package com.example.HRM.entity.erecruitment;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interviewSchedule")
public class InterviewSchedule {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long interviewScheduleId;
	  
	  private long candidateId;
	  
	  private String interviewerName;
	  
	  private String interviewerContact;
	  
	  private Date date;
	  
	  private String startTime;
	  
	  private String endTime;
	  
	  private String cancellationReason;
	  
	  private String interviewType;
	  
	  private String interviewFormat;
	  
	  private boolean Scheduled;
	  
	  private boolean Completed;
	  
	  private boolean Canceled;
	  
	  private boolean status;
	  
	  public long getInterviewScheduleId() {
	    return this.interviewScheduleId;
	  }
	  
	  public void setInterviewScheduleId(long interviewScheduleId) {
	    this.interviewScheduleId = interviewScheduleId;
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
	  
	  public String getCancellationReason() {
	    return this.cancellationReason;
	  }
	  
	  public void setCancellationReason(String cancellationReason) {
	    this.cancellationReason = cancellationReason;
	  }
	  
	  public String getInterviewType() {
	    return this.interviewType;
	  }
	  
	  public void setInterviewType(String interviewType) {
	    this.interviewType = interviewType;
	  }
	  
	  public String getInterviewFormat() {
	    return this.interviewFormat;
	  }
	  
	  public void setInterviewFormat(String interviewFormat) {
	    this.interviewFormat = interviewFormat;
	  }
	  
	  public boolean isScheduled() {
	    return this.Scheduled;
	  }
	  
	  public void setScheduled(boolean scheduled) {
	    this.Scheduled = scheduled;
	  }
	  
	  public boolean isCompleted() {
	    return this.Completed;
	  }
	  
	  public void setCompleted(boolean completed) {
	    this.Completed = completed;
	  }
	  
	  public boolean isCanceled() {
	    return this.Canceled;
	  }
	  
	  public void setCanceled(boolean canceled) {
	    this.Canceled = canceled;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
