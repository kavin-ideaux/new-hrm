package com.example.HRM.entity.clientDetails;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projectType")
public class ProjectType {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long projectTypeId;
	  
	  private String projectType;
	  
	  public long getProjectTypeId() {
	    return this.projectTypeId;
	  }
	  
	  public void setProjectTypeId(long projectTypeId) {
	    this.projectTypeId = projectTypeId;
	  }
	  
	  public String getProjectType() {
	    return this.projectType;
	  }
	  
	  public void setProjectType(String projectType) {
	    this.projectType = projectType;
	  }
	}

