package com.example.HRM.entity.clientDetails;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientRequirements")
public class ClientRequirement {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long projectId;
	  
	  private String projectName;
	  
	  private String duration;
	  
	  private long serviceId;
	  
	  private Date date;
	  
	  private long clientId;
	  
	  private boolean status;
	  
	  private Blob fileUpload;
	  
	  private String fileUploadUrl;
	  
	  private String projectStatus;
	  
	  private long projectTypeId;
	  
	  public String getProjectName() {
	    return this.projectName;
	  }
	  
	  public void setProjectName(String projectName) {
	    this.projectName = projectName;
	  }
	  
	  public long getProjectTypeId() {
	    return this.projectTypeId;
	  }
	  
	  public void setProjectTypeId(long projectTypeId) {
	    this.projectTypeId = projectTypeId;
	  }
	  
	  public boolean isStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(boolean status) {
	    this.status = status;
	  }
	  
	  public String getProjectStatus() {
	    return this.projectStatus;
	  }
	  
	  public void setProjectStatus(String projectStatus) {
	    this.projectStatus = projectStatus;
	  }
	  
	  public long getProjectId() {
	    return this.projectId;
	  }
	  
	  public void setProjectId(long projectId) {
	    this.projectId = projectId;
	  }
	  
	  public String getDuration() {
	    return this.duration;
	  }
	  
	  public void setDuration(String duration) {
	    this.duration = duration;
	  }
	  
	  public long getServiceId() {
	    return this.serviceId;
	  }
	  
	  public void setServiceId(long serviceId) {
	    this.serviceId = serviceId;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public long getClientId() {
	    return this.clientId;
	  }
	  
	  public void setClientId(long clientId) {
	    this.clientId = clientId;
	  }
	  
	  public Blob getFileUpload() {
	    return this.fileUpload;
	  }
	  
	  public void setFileUpload(Blob fileUpload) {
	    this.fileUpload = fileUpload;
	  }
	  
	  public String getFileUploadUrl() {
	    return this.fileUploadUrl;
	  }
	  
	  public void setFileUploadUrl(String fileUploadUrl) {
	    this.fileUploadUrl = fileUploadUrl;
	  }
	}


