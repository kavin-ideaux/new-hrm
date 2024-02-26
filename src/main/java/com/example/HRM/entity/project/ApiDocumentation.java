package com.example.HRM.entity.project;
import java.sql.Blob;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_documentation")
public class ApiDocumentation {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long apiDocumentationId;
	  
	  private long projectId;
	  
	  private long employeeId;
	  
	  private String documentationName;
	  
	  private Blob apiDocumentationUpload;
	  
	  private String apiDocumentationUrl;
	  
	  private Date date;
	  
	  public String getDocumentationName() {
	    return this.documentationName;
	  }
	  
	  public void setDocumentationName(String documentationName) {
	    this.documentationName = documentationName;
	  }
	  
	  public long getApiDocumentationId() {
	    return this.apiDocumentationId;
	  }
	  
	  public void setApiDocumentationId(long apiDocumentationId) {
	    this.apiDocumentationId = apiDocumentationId;
	  }
	  
	  public long getProjectId() {
	    return this.projectId;
	  }
	  
	  public void setProjectId(long projectId) {
	    this.projectId = projectId;
	  }
	  
	  public long getEmployeeId() {
	    return this.employeeId;
	  }
	  
	  public void setEmployeeId(long employeeId) {
	    this.employeeId = employeeId;
	  }
	  
	  public Blob getApiDocumentationUpload() {
	    return this.apiDocumentationUpload;
	  }
	  
	  public void setApiDocumentationUpload(Blob apiDocumentationUpload) {
	    this.apiDocumentationUpload = apiDocumentationUpload;
	  }
	  
	  public String getApiDocumentationUrl() {
	    return this.apiDocumentationUrl;
	  }
	  
	  public void setApiDocumentationUrl(String apiDocumentationUrl) {
	    this.apiDocumentationUrl = apiDocumentationUrl;
	  }
	  
	  public Date getDate() {
	    return this.date;
	  }
	  
	  public void setDate(Date date) {
	    this.date = date;
	  }
	

}
