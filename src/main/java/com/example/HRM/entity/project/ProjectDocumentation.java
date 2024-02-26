package com.example.HRM.entity.project;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_documentation")
public class ProjectDocumentation {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long projectDocumentationId;
	  
	  private long projectId;
	  
	  private Blob documentationUpload;
	  
	  private String documentationUrl;
	  
	  private long employeeId;
	  
	  private Date date;
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public long getProjectDocumentationId() {
	    return this.projectDocumentationId;
	  }
	  
	  public void setProjectDocumentationId(long projectDocumentationId) {
	    this.projectDocumentationId = projectDocumentationId;
	  }
	  
	  public long getProjectId() {
	    return this.projectId;
	  }
	  
	  public void setProjectId(long projectId) {
	    this.projectId = projectId;
	  }
	  
	  public Blob getDocumentationUpload() {
	    return this.documentationUpload;
	  }
	  
	  public void setDocumentationUpload(Blob documentationUpload) {
	    this.documentationUpload = documentationUpload;
	  }
	  
	  public String getDocumentationUrl() {
	    return this.documentationUrl;
	  }
	  
	  public void setDocumentationUrl(String documentationUrl) {
	    this.documentationUrl = documentationUrl;
	  }
	

}
