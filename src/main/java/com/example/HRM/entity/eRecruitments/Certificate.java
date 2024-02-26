package com.example.HRM.entity.eRecruitments;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "certificate")
public class Certificate {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long certificateId;
	  
	  private long traineeId;
	  
	  private String trainingProgram;
	  
	  private String hospitalName;
	  
	  private Date certificateIssuedDate;
	  
	  private Blob authorizedSignature;
	  
	  private Blob traineeSignature;
	  
	  private Blob officialLogo;
	  
	  private boolean status;
	  
	  private String authorizedSignatureUrl;
	  
	  private String traineeSignatureUrl;
	  
	  private String officialLogoUrl;
	  
	  public long getCertificateId() {
	    return this.certificateId;
	  }
	  
	  public void setCertificateId(long certificateId) {
	    this.certificateId = certificateId;
	  }
	  
	  public long getTraineeId() {
	    return this.traineeId;
	  }
	  
	  public void setTraineeId(long traineeId) {
	    this.traineeId = traineeId;
	  }
	  
	  public String getTrainingProgram() {
	    return this.trainingProgram;
	  }
	  
	  public void setTrainingProgram(String trainingProgram) {
	    this.trainingProgram = trainingProgram;
	  }
	  
	  public String getHospitalName() {
	    return this.hospitalName;
	  }
	  
	  public void setHospitalName(String hospitalName) {
	    this.hospitalName = hospitalName;
	  }
	  
	  public Date getCertificateIssuedDate() {
	    return this.certificateIssuedDate;
	  }
	  
	  public void setCertificateIssuedDate(Date certificateIssuedDate) {
	    this.certificateIssuedDate = certificateIssuedDate;
	  }
	  
	  public Blob getAuthorizedSignature() {
	    return this.authorizedSignature;
	  }
	  
	  public void setAuthorizedSignature(Blob authorizedSignature) {
	    this.authorizedSignature = authorizedSignature;
	  }
	  
	  public Blob getTraineeSignature() {
	    return this.traineeSignature;
	  }
	  
	  public void setTraineeSignature(Blob traineeSignature) {
	    this.traineeSignature = traineeSignature;
	  }
	  
	  public Blob getOfficialLogo() {
	    return this.officialLogo;
	  }
	  
	  public void setOfficialLogo(Blob officialLogo) {
	    this.officialLogo = officialLogo;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public String getAuthorizedSignatureUrl() {
	    return this.authorizedSignatureUrl;
	  }
	  
	  public void setAuthorizedSignatureUrl(String authorizedSignatureUrl) {
	    this.authorizedSignatureUrl = authorizedSignatureUrl;
	  }
	  
	  public String getTraineeSignatureUrl() {
	    return this.traineeSignatureUrl;
	  }
	  
	  public void setTraineeSignatureUrl(String traineeSignatureUrl) {
	    this.traineeSignatureUrl = traineeSignatureUrl;
	  }
	  
	  public String getOfficialLogoUrl() {
	    return this.officialLogoUrl;
	  }
	  
	  public void setOfficialLogoUrl(String officialLogoUrl) {
	    this.officialLogoUrl = officialLogoUrl;
	  }
	}


