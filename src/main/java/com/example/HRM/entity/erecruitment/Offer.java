package com.example.HRM.entity.erecruitment;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offerLetter")
public class Offer {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long offerId;
	  
	  private Date joiningDate;
	  
	  private Date expiryDate;
	  
	  private String salaryPackage;
	  
	  private boolean acceptanceStatus;
	  
	  private long candidateId;
	  
	  private long appointmentId;
	  
	  private boolean status;
	  
	  private Blob certificate;
	  
	  private String certificateUrl;
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public Blob getCertificate() {
	    return this.certificate;
	  }
	  
	  public void setCertificate(Blob certificate) {
	    this.certificate = certificate;
	  }
	  
	  public String getCertificateUrl() {
	    return this.certificateUrl;
	  }
	  
	  public void setCertificateUrl(String certificateUrl) {
	    this.certificateUrl = certificateUrl;
	  }
	  
	  public long getOfferId() {
	    return this.offerId;
	  }
	  
	  public void setOfferId(long offerId) {
	    this.offerId = offerId;
	  }
	  
	  public Date getJoiningDate() {
	    return this.joiningDate;
	  }
	  
	  public void setJoiningDate(Date joiningDate) {
	    this.joiningDate = joiningDate;
	  }
	  
	  public Date getExpiryDate() {
	    return this.expiryDate;
	  }
	  
	  public void setExpiryDate(Date expiryDate) {
	    this.expiryDate = expiryDate;
	  }
	  
	  public String getSalaryPackage() {
	    return this.salaryPackage;
	  }
	  
	  public void setSalaryPackage(String salaryPackage) {
	    this.salaryPackage = salaryPackage;
	  }
	  
	  public boolean isAcceptanceStatus() {
	    return this.acceptanceStatus;
	  }
	  
	  public void setAcceptanceStatus(boolean acceptanceStatus) {
	    this.acceptanceStatus = acceptanceStatus;
	  }
	  
	  public long getCandidateId() {
	    return this.candidateId;
	  }
	  
	  public void setCandidateId(long candidateId) {
	    this.candidateId = candidateId;
	  }
	  
	  public long getAppointmentId() {
	    return this.appointmentId;
	  }
	  
	  public void setAppointmentId(long appointmentId) {
	    this.appointmentId = appointmentId;
	  }
	

}
