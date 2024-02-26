package com.example.HRM.entity.erecruitment;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long appointmentId;
	  
	  private long candidateId;
	  
	  private Date date;
	  
	  private Time time;
	  
	  private String position;
	  
	  private boolean confirmationStatus;
	  
	  private long hrInterviewId;
	  
	  private boolean status;
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public long getAppointmentId() {
	    return this.appointmentId;
	  }
	  
	  public void setAppointmentId(long appointmentId) {
	    this.appointmentId = appointmentId;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
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
	  
	  public String getPosition() {
	    return this.position;
	  }
	  
	  public void setPosition(String position) {
	    this.position = position;
	  }
	  
	  public boolean isConfirmationStatus() {
	    return this.confirmationStatus;
	  }
	  
	  public void setConfirmationStatus(boolean confirmationStatus) {
	    this.confirmationStatus = confirmationStatus;
	  }
	  
	  public long getHrInterviewId() {
	    return this.hrInterviewId;
	  }
	  
	  public void setHrInterviewId(long hrInterviewId) {
	    this.hrInterviewId = hrInterviewId;
	  }
	

}
