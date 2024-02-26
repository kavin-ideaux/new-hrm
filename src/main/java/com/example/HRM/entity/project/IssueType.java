package com.example.HRM.entity.project;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issueType")
public class IssueType {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private long issueTypeId;
	  
	  private String issuename;
	  
	  public long getIssueTypeId() {
	    return this.issueTypeId;
	  }
	  
	  public void setIssueTypeId(long issueTypeId) {
	    this.issueTypeId = issueTypeId;
	  }
	  
	  public String getIssuename() {
	    return this.issuename;
	  }
	  
	  public void setIssuename(String issuename) {
	    this.issuename = issuename;
	  }
	

}
