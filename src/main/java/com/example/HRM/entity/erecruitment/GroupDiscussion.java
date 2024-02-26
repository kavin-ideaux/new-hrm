package com.example.HRM.entity.erecruitment;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "groupDiscussion")
public class GroupDiscussion {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long groupDiscussionId;
	  
	  private long candidateId;
	  
	  private String topic;
	  
	  private Date date;
	  
	  private Time time;
	  
	  private String feedback;
	  
	  private String rules;
	  
	  private String format;
	  
	  private boolean started;
	  
	  private boolean inProgress;
	  
	  private boolean completed;
	  
	  private boolean status;
	  
	  private long taskId;
	  
	  public long getGroupDiscussionId() {
	    return this.groupDiscussionId;
	  }
	  
	  public void setGroupDiscussionId(long groupDiscussionId) {
	    this.groupDiscussionId = groupDiscussionId;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	  
	  public String getTopic() {
	    return this.topic;
	  }
	  
	  public void setTopic(String topic) {
	    this.topic = topic;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public Time getTime() {
	    return this.time;
	  }
	  
	  public void setTime(Time time) {
	    this.time = time;
	  }
	  
	  public String getFeedback() {
	    return this.feedback;
	  }
	  
	  public void setFeedback(String feedback) {
	    this.feedback = feedback;
	  }
	  
	  public String getRules() {
	    return this.rules;
	  }
	  
	  public void setRules(String rules) {
	    this.rules = rules;
	  }
	  
	  public String getFormat() {
	    return this.format;
	  }
	  
	  public void setFormat(String format) {
	    this.format = format;
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
	  
	  public long getTaskId() {
	    return this.taskId;
	  }
	  
	  public void setTaskId(long taskId) {
	    this.taskId = taskId;
	  }
	

}
