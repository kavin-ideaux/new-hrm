package com.example.HRM.entity.eRecruitments;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "traineeDetails")
public class TraineeDetails {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long traineeId;
	  
	  private String traineeName;
	  
	  private String emailId;
	  
	  private String mobileNumber;
	  
	  private String collegeName;
	  
	  private Date startDate;
	  
	  private Date endDate;
	  
	  private boolean started;
	  
	  private boolean completed;
	  
	  private boolean status;
	  
	  public long getTraineeId() {
	    return this.traineeId;
	  }
	  
	  public void setTraineeId(long traineeId) {
	    this.traineeId = traineeId;
	  }
	  
	  public String getTraineeName() {
	    return this.traineeName;
	  }
	  
	  public void setTraineeName(String traineeName) {
	    this.traineeName = traineeName;
	  }
	  
	  public String getEmailId() {
	    return this.emailId;
	  }
	  
	  public void setEmailId(String emailId) {
	    this.emailId = emailId;
	  }
	  
	  public String getMobileNumber() {
	    return this.mobileNumber;
	  }
	  
	  public void setMobileNumber(String mobileNumber) {
	    this.mobileNumber = mobileNumber;
	  }
	  
	  public String getCollegeName() {
	    return this.collegeName;
	  }
	  
	  public void setCollegeName(String collegeName) {
	    this.collegeName = collegeName;
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
