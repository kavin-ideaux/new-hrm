package com.example.HRM.entity.project;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hosting")
public class TestingAndHosting {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long testId;
	  
	  private long apiDocumentationId;
	  
	  private Date date;
	  
	  private long employeeId;
	  
	  private String projectStatus;
	  
	  private boolean todo;
	  
	  private boolean onProcess;
	  
	  private boolean error;
	  
	  private boolean movedHosting;
	  
	  private boolean hostingProcess;
	  
	  private boolean hostingCompled;
	  
	  public long getTestId() {
	    return this.testId;
	  }
	  
	  public void setTestId(long testId) {
	    this.testId = testId;
	  }
	  
	  public long getApiDocumentationId() {
	    return this.apiDocumentationId;
	  }
	  
	  public void setApiDocumentationId(long apiDocumentationId) {
	    this.apiDocumentationId = apiDocumentationId;
	  }
	  
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
	  
	  public String getProjectStatus() {
	    return this.projectStatus;
	  }
	  
	  public void setProjectStatus(String projectStatus) {
	    this.projectStatus = projectStatus;
	  }
	  
	  public boolean isTodo() {
	    return this.todo;
	  }
	  
	  public void setTodo(boolean todo) {
	    this.todo = todo;
	  }
	  
	  public boolean isOnProcess() {
	    return this.onProcess;
	  }
	  
	  public void setOnProcess(boolean onProcess) {
	    this.onProcess = onProcess;
	  }
	  
	  public boolean isError() {
	    return this.error;
	  }
	  
	  public void setError(boolean error) {
	    this.error = error;
	  }
	  
	  public boolean isMovedHosting() {
	    return this.movedHosting;
	  }
	  
	  public void setMovedHosting(boolean movedHosting) {
	    this.movedHosting = movedHosting;
	  }
	  
	  public boolean isHostingProcess() {
	    return this.hostingProcess;
	  }
	  
	  public void setHostingProcess(boolean hostingProcess) {
	    this.hostingProcess = hostingProcess;
	  }
	  
	  public boolean isHostingCompled() {
	    return this.hostingCompled;
	  }
	  
	  public void setHostingCompled(boolean hostingCompled) {
	    this.hostingCompled = hostingCompled;
	  }
	

}
