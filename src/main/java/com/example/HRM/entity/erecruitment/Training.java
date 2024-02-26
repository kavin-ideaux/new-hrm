package com.example.HRM.entity.erecruitment;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trainingDetails")
public class Training {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long traineeId;
	  
	  private long candidateId;
	  
	  private long offerId;
	  
	  private Date startDate;
	  
	  private Date endDate;
	  
	  private boolean started;
	  
	  private boolean inProgress;
	  
	  private boolean completed;
	  
	  private long roleId;
	  
	  private String password;
	  
	  private String confirmPassword;
	  
	  private boolean status;
	  
	  public long getRoleId() {
	    return this.roleId;
	  }
	  
	  public void setRoleId(long roleId) {
	    this.roleId = roleId;
	  }
	  
	  public String getPassword() {
	    return this.password;
	  }
	  
	  public void setPassword(String password) {
	    this.password = password;
	  }
	  
	  public String getConfirmPassword() {
	    return this.confirmPassword;
	  }
	  
	  public void setConfirmPassword(String confirmPassword) {
	    this.confirmPassword = confirmPassword;
	  }
	  
	  public long getTraineeId() {
	    return this.traineeId;
	  }
	  
	  public void setTraineeId(long traineeId) {
	    this.traineeId = traineeId;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	  
	  public long getOfferId() {
	    return this.offerId;
	  }
	  
	  public void setOfferId(long offerId) {
	    this.offerId = offerId;
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
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	

}
